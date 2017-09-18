package com.anigenero.sandbox.poker.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertyUtil {

    private final Environment environment;

    @Autowired
    public PropertyUtil(Environment environment) {
        this.environment = environment;
    }

    /**
     * Gets the property using the property name
     *
     * @param propertyName {@link String}
     * @return {@link String}
     */
    public String getProperty(String propertyName) {
        return environment.getProperty(propertyName);
    }

    /**
     * Gets the property using the property name
     *
     * @param propertyName {@link String}
     * @return {@link String}
     */
    public String getProperty(String propertyName, String defaultValue) {

        String value = environment.getProperty(propertyName);
        if (value == null) {
            return defaultValue;
        }

        return value;

    }

}
