package com.anigenero.sandbox.poker.core.event;

import com.anigenero.sandbox.poker.core.constant.PlayEvent;
import com.anigenero.sandbox.poker.core.model.GameState;

import java.io.Serializable;

public class PokerEvent implements Serializable {

    private final GameState state;
    private final PlayEvent event;

    public PokerEvent(GameState state, PlayEvent event) {
        this.state = state;
        this.event = event;
    }

    public GameState getState() {
        return state;
    }

    public PlayEvent getEvent() {
        return event;
    }

}
