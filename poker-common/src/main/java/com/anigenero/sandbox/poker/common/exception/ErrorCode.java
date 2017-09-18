package com.anigenero.sandbox.poker.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ErrorCode implements Serializable {

    private static final Logger log = LogManager.getLogger(ErrorCode.class);

    static {

        errorLocaleMap = new ConcurrentHashMap<>();
        buildErrorMessageLocaleMap(Locale.getDefault());

    }

    private static Map<Locale, ResourceBundle> errorLocaleMap;

    private final String code;
    private final ErrorCode parent;

    /**
     * Builds with an error code
     *
     * @param code {@link String}
     */
    public ErrorCode(String code) {
        this.code = code;
        this.parent = null;
    }

    /**
     * Builds with an error code and parent
     *
     * @param code   {@link String}
     * @param parent {@link ErrorCode}
     */
    public ErrorCode(String code, ErrorCode parent) {
        this.code = code;
        this.parent = parent;
    }

    /**
     * Gets the error code with parents attached
     *
     * @return {@link String}
     */
    public String getErrorCode() {

        // assemble the
        List<String> errorCodes = new ArrayList<>(1);
        ErrorCode currentParent = this.parent;
        while (currentParent != null) {

            errorCodes.add(0, currentParent.code);
            currentParent = currentParent.parent;

        }

        errorCodes.add(this.code);

        return StringUtils.join(errorCodes, ".");

    }

    /**
     * Gets the localized error message
     *
     * @return {@link String}
     */
    public String getMessage() {

        ResourceBundle resourceBundle = getCurrentLocalePropertyMap();
        if (resourceBundle != null) {
            return resourceBundle.getString(getErrorCode());
        } else {
            return getErrorCode();
        }

    }

    /**
     * Gets the {@link ResourceBundle} for the locale
     *
     * @return {@link ResourceBundle}
     */
    private ResourceBundle getCurrentLocalePropertyMap() {

        // TODO: determine locale
        Locale locale = Locale.getDefault();

        if (errorLocaleMap.containsKey(locale)) {
            return errorLocaleMap.get(locale);
        } else {
            return buildErrorMessageLocaleMap(locale);
        }

    }

    /**
     * Builds the error message locale map
     *
     * @param locale {@link Locale}
     * @return {@link ResourceBundle}
     */
    private static ResourceBundle buildErrorMessageLocaleMap(Locale locale) {

        try {

            ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/error/ErrorMessages", locale);
            errorLocaleMap.put(locale, resourceBundle);
            return resourceBundle;

        } catch (MissingResourceException e) {
            log.error("Missing error message resource", e);
            return null;
        }

    }

    public static class Codes {

        public static final ErrorCode UNKNOWN = new ErrorCode("unknown");

        private Codes() { }

    }

}
