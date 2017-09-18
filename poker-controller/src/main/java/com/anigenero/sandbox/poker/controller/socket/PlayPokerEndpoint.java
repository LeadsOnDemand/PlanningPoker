package com.anigenero.sandbox.poker.controller.socket;

import com.anigenero.sandbox.poker.controller.handler.PlayPokerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.QueryParam;
import java.io.IOException;

@ServerEndpoint(value = "/play", configurator = SpringConfigurator.class)
public class PlayPokerEndpoint {

    private final PlayPokerHandler playPokerHandler;

    @Autowired
    public PlayPokerEndpoint(PlayPokerHandler playPokerHandler) {
        this.playPokerHandler = playPokerHandler;
    }

    @OnOpen
    public void onOpen(@QueryParam("name") String name, Session session) throws IOException {
        this.playPokerHandler.registerSession(name, session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {

    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {

    }

}
