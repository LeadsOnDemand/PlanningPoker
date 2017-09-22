package com.anigenero.sandbox.poker.dao.jpa.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class JPATestingBase<V, K> {

    private static final Logger log = LogManager.getLogger(JPATestingBase.class);

    private static Map<Class, SingularAttribute> idAttributeMap = new HashMap<>();

    private static EntityManager entityManager;

    private Class<V> persistentClass;

    @SuppressWarnings("unchecked")
    public JPATestingBase() {
        this.persistentClass = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @BeforeClass
    public static void init() throws FileNotFoundException, SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterClass
    public static void tearDown() {

        entityManager.clear();
        entityManager.close();

    }

    /**
     * Gets the entity manager
     *
     * @return {@link EntityManager}
     */
    public static EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Gets the entity by ID
     *
     * @param id K - the ID of the entity
     * @return V - the entity value
     */
    protected V getById(K id) {

        SingularAttribute<V, K> singularAttribute = determineEntityId(persistentClass);
        if (singularAttribute != null) {
            return getEntityByField(persistentClass, singularAttribute, id);
        }

        return null;

    }

    /**
     * Persists an entity to the database within a transaction and returns the result
     *
     * @param value T - the entity to persist
     * @param <T>   the type to persist
     * @return T - the persisted value
     */
    protected <T> T persist(T value) {

        entityManager.getTransaction().begin();
        entityManager.persist(value);
        entityManager.getTransaction().commit();

        entityManager.refresh(value);

        return value;

    }

    /**
     * Gets all the elements in the database
     *
     * @return {@link List} of V
     */
    protected List<V> getAll() {
        return getAll(persistentClass);
    }

    /**
     * Gets all of the entity type
     *
     * @param clazz {@link Class} the entity type class
     * @param <T>   entity type
     * @return T - the vale
     */
    protected <T> List<T> getAll(Class<T> clazz) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);

        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();

    }

    /**
     * Gets the entity by field. If no entity exists, return null
     *
     * @param entityClass {@link Class}
     * @param field       {@link SingularAttribute}
     * @param value       the value
     * @param <T>         the entity type
     * @return the entity <T>
     */
    <T> V getEntityByField(Class<V> entityClass, SingularAttribute<V, T> field, T value) {

        try {

            CriteriaQuery<V> criteriaQuery = buildCriteriaQueryForField(entityClass, field, value);
            return entityManager.createQuery(criteriaQuery).getSingleResult();

        } catch (NoResultException e) {
            return null;
        }

    }

    /**
     * Builds a criteria query for a specific field
     *
     * @param entityClass {@link Class}
     * @param field       {@link SingularAttribute}
     * @param value       V - the value
     * @param <T>         the entity class type
     * @return {@link CriteriaQuery}
     */
    private <T> CriteriaQuery<V> buildCriteriaQueryForField(Class<V> entityClass, SingularAttribute<V, T> field, T value) {

        CriteriaQuery<V> criteriaQuery = getCriteriaQuery(entityClass);
        Root<V> root = criteriaQuery.from(entityClass);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery.where(criteriaBuilder.equal(root.get(field), value));

        return criteriaQuery;

    }

    /**
     * Creates a criteria query for the specified entity class
     *
     * @param entityClass {@link Class}
     * @return {@link Criteria}
     */
    private CriteriaQuery<V> getCriteriaQuery(Class<V> entityClass) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        return criteriaBuilder.createQuery(entityClass);

    }

    /**
     * Gets the entity ID attribute from the specified entity class
     *
     * @param entityClass {@link Class}
     * @return {@link SingularAttribute}
     */
    private SingularAttribute<V, K> determineEntityId(Class<V> entityClass) {

        // if we've already determined the ID attribute, return it
        if (idAttributeMap.containsKey(entityClass)) {
            return idAttributeMap.get(entityClass);
        }

        // handle each declared field
        // if the class is annotated with @Id, return the attribute
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.getAnnotation(Id.class) != null) {
                try {

                    SingularAttribute<V, K> attribute = getSingularAttributeFromField(entityClass, field);
                    idAttributeMap.put(entityClass, attribute);
                    return attribute;

                } catch (ClassNotFoundException e) {
                    log.error("Could not find meta model class for {}", entityClass, e);
                    return null;
                } catch (NoSuchFieldException e) {
                    log.error("Could not find meta model ID field for entity {}", entityClass, e);
                    return null;
                } catch (IllegalAccessException e) {
                    log.error("Could not access ID field for entity {}", entityClass, e);
                    return null;
                }
            }
        }

        return null;

    }

    /**
     * Gets the attribute for the specified field
     *
     * @param entityClass {@link Class}
     * @param field       {@link Field}
     * @param <V>         the entity type
     * @param <K>         the entity attribute value type
     * @return {@link SingularAttribute}
     * @throws ClassNotFoundException if the JPA generated meta model is not found
     * @throws NoSuchFieldException   if the field doesn't exist
     * @throws IllegalAccessException if the field cannot be accessed
     */
    @SuppressWarnings("unchecked")
    private <V, K> SingularAttribute<V, K> getSingularAttributeFromField(Class<V> entityClass, Field field)
            throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Class metaModelClass = Class.forName(entityClass.getName() + "_");
        Field metaModelField = metaModelClass.getDeclaredField(field.getName());
        return (SingularAttribute<V, K>) metaModelField.get(null);

    }

}
