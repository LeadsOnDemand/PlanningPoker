package com.anigenero.sandbox.poker.core.socket;

import javax.websocket.Session;
import java.io.Serializable;

public class WebSocketSession implements Serializable {

    private final Session session;
    private final String name;

    public WebSocketSession(String name, Session session) {
        this.name = name;
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public String getName() {
        return name;
    }

}
