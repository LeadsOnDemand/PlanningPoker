package com.anigenero.sandbox.poker.core.socket;

import javax.websocket.Session;
import java.io.Serializable;

public class PokerSession implements Serializable {

    private final Session session;
    private final String name;

    private final boolean leader;

    public PokerSession(String name, Session session, boolean leader) {
        this.leader = leader;
        this.name = name;
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public String getName() {
        return name;
    }

    public boolean isLeader() {
        return leader;
    }

}
