package com.anigenero.sandbox.poker.controller.socket;

import com.anigenero.sandbox.poker.controller.handler.PlayPokerHandler;
import com.anigenero.sandbox.poker.controller.socket.config.WebSocketConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Controller
@ServerEndpoint(value = "/play/{username}", configurator = WebSocketConfigurator.class, encoders = {WebSocketEncoder.class})
public class PlayPokerEndpoint {

    private final PlayPokerHandler playPokerHandler;

    @Autowired
    public PlayPokerEndpoint(PlayPokerHandler playPokerHandler) {
        this.playPokerHandler = playPokerHandler;
    }

    @OnMessage
    public void onMessage(final Session session, String message) {

    }

    @OnOpen
    public void onOpen(final Session session, @PathParam("username") final String username) {
        this.playPokerHandler.registerSession(username, session);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    }

    @OnError
    public void onError(Session session, Throwable thr) {
    }

}
