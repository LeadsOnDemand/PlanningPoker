package com.anigenero.sandbox.poker.controller.handler;

import com.anigenero.sandbox.poker.controller.model.PokerEstimateDTO;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

public interface PlayPokerHandler {

    /**
     * Checks if there is a slot available for the pokr game
     *
     * @return boolean - true if the slot is available, else false
     */
    boolean isSlotAvailable();

    /**
     * Registers the session of the incoming user
     *
     * @param name    {@link String}
     * @param session {@link Session}
     */
    void registerSession(String name, Session session);

    /**
     * Sets the name of the current task that needs estimates
     *
     * @param taskName {@link String}
     * @param request  {@link HttpServletRequest}
     */
    void startNewRound(String taskName, HttpServletRequest request);

    /**
     * Submit the estimate for the specified story
     *
     * @param pokerEstimateDTO {@link PokerEstimateDTO}
     * @param request          {@link Session}
     */
    void submitEstimate(PokerEstimateDTO pokerEstimateDTO, HttpServletRequest request);

    /**
     * Terminates the current session
     *
     * @param session {@link Session}
     */
    void terminateSession(Session session);

}
