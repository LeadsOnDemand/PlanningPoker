package com.anigenero.sandbox.poker.core.model;

import java.io.Serializable;

public class UserSession implements Serializable {

    private String sessionId;
    private String username;

    public UserSession() {
    }

    public UserSession(String sessionId, String username) {
        this.sessionId = sessionId;
        this.username = username;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUsername() {
        return username;
    }

}
