package com.anigenero.sandbox.poker.dao.config;

import com.anigenero.sandbox.poker.common.util.PropertyUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

@Configuration
@ImportResource("classpath:daoContext.xml")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class PersistConfig {

    private static final Logger log = LogManager.getLogger(PersistConfig.class);

    private final PropertyUtil propertyUtil;

    @Value("${sql.debug}")
    private Boolean sqlDebug;

    @Autowired
    public PersistConfig(PropertyUtil propertyUtil) {
        this.propertyUtil = propertyUtil;
    }

    @Bean
    public EntityManagerFactory platformEntityManager(@Qualifier("platformDataSource") DataSource dataSource)
            throws Exception {

        final LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setPersistenceUnitName("platform");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManager.setDataSource(dataSource);
        entityManager.setPackagesToScan("com.anigenero.sandbox.poker.dao.jpa.entity");
        entityManager.setJpaProperties(jpaProperties());
        entityManager.afterPropertiesSet();

        return entityManager.getObject();

    }

    private Properties jpaProperties() {

        Properties jpaProperties = new Properties();

        jpaProperties.put(AvailableSettings.DIALECT, resolveDialect());
        jpaProperties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true);
        jpaProperties.put(AvailableSettings.AUTOCOMMIT, false);
        jpaProperties.put(AvailableSettings.ISOLATION, Connection.TRANSACTION_READ_COMMITTED);

        // we're gonna drop the schema every time, but this is a test application - so who cares
        jpaProperties.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");

        // if we have set the debg flag on SQL, let's debug the output
        if (Boolean.TRUE.equals(sqlDebug)) {
            jpaProperties.put(AvailableSettings.SHOW_SQL, true);
            jpaProperties.put(AvailableSettings.FORMAT_SQL, true);
        }

        return jpaProperties;

    }

    private String resolveDialect() {

        String dialect = propertyUtil.getProperty("db.hql.dialect");
        if (StringUtils.isNotEmpty(dialect)) {
            try {
                Class.forName(dialect);
                return dialect;
            } catch (Exception e) {
                log.warn("Error setting hibernate dialect: {}. Using default", e);
            }
        }

        return H2Dialect.class.getName();

    }

}
