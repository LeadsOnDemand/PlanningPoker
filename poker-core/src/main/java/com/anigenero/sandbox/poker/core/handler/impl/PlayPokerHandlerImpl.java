package com.anigenero.sandbox.poker.core.handler.impl;

import com.anigenero.sandbox.poker.controller.handler.PlayPokerHandler;
import com.anigenero.sandbox.poker.controller.model.PokerCardDTO;
import com.anigenero.sandbox.poker.core.event.*;
import com.anigenero.sandbox.poker.core.model.PokerTask;
import com.anigenero.sandbox.poker.core.model.UserSession;
import com.anigenero.sandbox.poker.core.socket.PokerSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PlayPokerHandlerImpl implements PlayPokerHandler {

    private static final Logger log = LogManager.getLogger(PlayPokerHandler.class);

    private static final int MAX_USERS = 4;

    private final AuthenticationHandlerImpl authenticationHandler;
    private final Map<String, PokerSession> sessionMap;

    private PokerTask pokerTask;

    @Autowired
    public PlayPokerHandlerImpl(AuthenticationHandlerImpl authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
        this.sessionMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isSlotAvailable() {
        return this.sessionMap.size() < MAX_USERS;
    }

    @Override
    public void registerSession(String name, Session session) {

        if (!this.isSlotAvailable()) {

            try {
                session.close();
            } catch (IOException e) {
                log.error("Could not close session because of an error", e);
            }

        } else if (!this.sessionMap.containsKey(session.getId())) {

            // we want to ensure multiple incoming "first" requests don't overlap
            synchronized (this.sessionMap) {

                // set as the game leader if there are currently no users in the game
                final boolean isGameLeader = this.sessionMap.isEmpty();

                final PokerSession socketSession = new PokerSession(name, session, isGameLeader);
                this.sessionMap.put(session.getId(), socketSession);

                try {

                    final String token = this.authenticationHandler.issueToken(session.getId(), name);

                    SessionCreatedEvent.SessionInfo sessionInfo = new SessionCreatedEvent.SessionInfo();
                    sessionInfo.setLeader(isGameLeader);
                    sessionInfo.setToken(token);

                    session.getBasicRemote().sendObject(new SessionCreatedEvent(name, sessionInfo));

                    this.sendEvent(new PlayerJoinedEvent(name));

                } catch (Exception e) {
                    log.error("Could not send connection response because of an error", e);
                }

            }

        }

    }

    @Override
    public void startNewRound(String taskName, HttpServletRequest request) {

        final UserSession userSession = this.authenticationHandler.getUserSession(request);

        // if this is not the session leader, we don't want to set anything
        final Session session = this.sessionMap.get(userSession.getSessionId()).getSession();
        if (!this.isGameLeader(session)) {
            return;
        }

        this.pokerTask = new PokerTask();
        this.pokerTask.setName(taskName);

        this.sendEvent(new NewDealEvent(userSession.getUsername(), taskName));

    }

    /**
     * Checks if the current session is the game leader
     *
     * @param session {@link Session}
     * @return boolean - true if leader, else false
     */
    private boolean isGameLeader(final Session session) {
        return this.sessionMap.containsKey(session.getId()) && this.sessionMap.get(session.getId()).isLeader();
    }

    @Override
    public void submitEstimate(PokerCardDTO pokerCardDTO, HttpServletRequest request) {

        final UserSession userSession = this.authenticationHandler.getUserSession(request);

        if (this.pokerTask == null || this.pokerTask.getEstimates().containsKey(userSession.getSessionId())) {
            return;
        }

        this.pokerTask.getEstimates().put(userSession.getSessionId(), pokerCardDTO);

        sendEvent(new SubmitEstimateEvent(userSession.getUsername(), this.pokerTask.getEstimates()));

        if (this.pokerTask.getEstimates().size() == this.sessionMap.size()) {
            this.sendEvent(new RevealCardsEvent(userSession.getUsername()));
        }

    }

    @Override
    public void terminateSession(final Session session) {

        final PokerSession pokerSession = this.sessionMap.get(session.getId());
        if (pokerSession.isLeader()) {
            this.terminatePlay();
        } else {

            final String id = session.getId();

            this.sendEvent(new PlayerQuitEvent(pokerSession.getName()));
            this.sessionMap.remove(id);

            try {
                session.close();
            } catch (IOException e) {
                log.error("Could not close session '{}' because of an error", id, e);
            }

        }

    }

    /**
     * Terminates play for the all the players
     */
    private void terminatePlay() {
        this.sessionMap.forEach((id, session) -> {
            try {
                session.getSession().close();
            } catch (IOException e) {
                log.error("Could not close session '{}' because of an error", id, e);
            } finally {
                this.sessionMap.remove(id);
            }
        });
    }

    private void sendEvent(final PokerEvent event) {
        this.sessionMap.forEach((id, session) -> {
            try {
                if (session.getSession().isOpen()) {
                    session.getSession().getBasicRemote().sendObject(event);
                }
            } catch (IOException | EncodeException e) {
                log.error("An error occurred sending event to remote session '{}'", id, e);
            }
        });
    }

}
