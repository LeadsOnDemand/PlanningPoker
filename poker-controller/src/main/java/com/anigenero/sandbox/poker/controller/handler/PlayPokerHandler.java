package com.anigenero.sandbox.poker.controller.handler;

import com.anigenero.sandbox.poker.controller.model.PokerEstimateDTO;

import javax.websocket.Session;

public interface PlayPokerHandler {

    /**
     * Checks if there is a slot available for the poker game
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
     * Submit the estimate for the specified story
     *
     * @param pokerEstimateDTO {@link PokerEstimateDTO}
     * @param session          {@link Session}
     */
    void submitEstimate(PokerEstimateDTO pokerEstimateDTO, Session session);

}
