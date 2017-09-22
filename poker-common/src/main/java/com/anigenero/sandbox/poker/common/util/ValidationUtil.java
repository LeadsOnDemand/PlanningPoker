package com.anigenero.sandbox.poker.common.util;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

import javax.validation.*;
import java.util.Set;

public class ValidationUtil {

    private static Validator validator;

    private ValidationUtil() { }

    /**
     * Validates a specified
     *
     * @param value T - the object to validate
     * @throws ValidationException when the bean is not valid
     */
    public static <T> void validate(T value) {

        Set<ConstraintViolation<T>> violations = getValidator().validate(value);
        if (violations == null || violations.isEmpty()) {
            return;
        }

        throw new ValidationException(getViolationErrorMessage(violations));

    }

    /**
     *
     * @param violations {@link Set} of {@link ConstraintViolation}
     * @param <T> the validation object type
     * @return {@link String}
     */
    private static <T> String getViolationErrorMessage(Set<ConstraintViolation<T>> violations) {

        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation constraintViolation : violations) {
            builder.append(constraintViolation.getMessage());
            builder.append("\n");
        }

        return builder.toString();

    }

    /**
     * Gets the validator for the
     *
     * @return {@link Validator}
     */
    private static Validator getValidator() {

        if (ValidationUtil.validator == null) {

            HibernateValidatorConfiguration config = Validation.byProvider(HibernateValidator.class).configure();
            ValidatorFactory factory = config.buildValidatorFactory();
            ValidationUtil.validator = factory.getValidator();

        }

        return ValidationUtil.validator;

    }

}
