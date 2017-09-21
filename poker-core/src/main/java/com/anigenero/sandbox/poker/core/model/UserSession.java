package com.anigenero.sandbox.poker.core.model;

import java.io.Serializable;

public class UserSession implements Serializable {

    private final String sessionId;
    private final String username;

    public UserSession(String sessionId, String username) {
        this.sessionId = sessionId;
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUsername() {
        return username;
    }

}
