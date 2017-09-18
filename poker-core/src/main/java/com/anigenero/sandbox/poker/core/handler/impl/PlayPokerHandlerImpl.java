package com.anigenero.sandbox.poker.core.handler.impl;

import com.anigenero.sandbox.poker.controller.handler.PlayPokerHandler;
import com.anigenero.sandbox.poker.controller.model.PokerEstimateDTO;
import com.anigenero.sandbox.poker.core.socket.WebSocketSession;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PlayPokerHandlerImpl implements PlayPokerHandler {

    private static final int MAX_USERS = 4;

    private final Map<String, WebSocketSession> sessionMap;

    public PlayPokerHandlerImpl() {
        this.sessionMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isSlotAvailable() {
        return this.sessionMap.size() < MAX_USERS;
    }

    @Override
    public void registerSession(String name, Session session) {

        synchronized (sessionMap) {

            if (!this.sessionMap.containsKey(session.getId())) {

                final WebSocketSession socketSession = new WebSocketSession(name, session);
                this.sessionMap.put(session.getId(), socketSession);

            }

        }

    }

    @Override
    public void submitEstimate(PokerEstimateDTO pokerEstimateDTO, Session session) {



    }

}
