package com.anigenero.sandbox.poker.common.util;

import java.security.SecureRandom;

public final class StringDataUtil {

    private static final SecureRandom random = new SecureRandom();

    private StringDataUtil () { }

    /**
     * Generates a random string
     *
     * @param length         int
     * @param includeNumbers boolean
     * @return {@link String}
     */
    public static String generateRandom(int length, boolean includeNumbers) {

        int path;
        char[] charArray = new char[length];
        for (int i = 0; i < length; i++) {

            path = includeNumbers ? random(0, 1) : 1;

            if (path == 0) {
                charArray[i] = (char) random(49, 57);
            } else {

                if (random(0, 1) == 0) {
                    charArray[i] = (char) random(65, 90);
                } else {
                    charArray[i] = (char) random(97, 122);
                }

            }

        }

        return new String(charArray);

    }

    /**
     * Gets a random integer from the specified integer
     *
     * @param min int - the max integer
     * @param max int - the min integer
     * @return int
     */
    private static int random(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Checks if the {@link String} is a type of number
     *
     * @param value {@link String}
     * @return boolean - true if number, else false
     */
    public static boolean isNumber(String value) {

        // if the value is null, return false
        if (value == null) {
            return false;
        }

        char currentChar;

        // check each character
        int count = value.length();
        for (int i = 0; i < count; i++) {

            currentChar = value.charAt(i);

            // if the character is not a number between 0-9
            // if first character and is not '-', return false
            if (!(currentChar >= '0' && currentChar <= '9')) {
                if (i == 0 && currentChar != '-') {
                    return false;
                } else if (i != 0) {
                    return false;
                }
            }

        }

        return true;

    }

}
