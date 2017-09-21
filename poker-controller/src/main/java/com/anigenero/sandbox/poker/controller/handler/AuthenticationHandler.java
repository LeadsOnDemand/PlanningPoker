package com.anigenero.sandbox.poker.controller.handler;

public interface AuthenticationHandler {

    /**
     * Determines if the session is valid
     * @param token {@link String}
     * @return boolean - true if valid, else false if anonymous or bad access
     */
    boolean isSessionValid(String token);

}
