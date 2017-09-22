package com.anigenero.sandbox.poker.core.event;

import com.anigenero.sandbox.poker.core.constant.PlayEvent;

import java.io.Serializable;

public class SessionCreatedEvent implements Serializable {

    private final SessionInfo data;

    public SessionCreatedEvent(SessionInfo data) {
        this.data = data;
    }

    public SessionInfo getData() {
        return data;
    }

    public PlayEvent getEvent() {
        return PlayEvent.SESSION_STARTED;
    }

    public static final class SessionInfo implements Serializable {

        private String token;
        private boolean leader;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public boolean isLeader() {
            return leader;
        }

        public void setLeader(boolean leader) {
            this.leader = leader;
        }

    }

}
