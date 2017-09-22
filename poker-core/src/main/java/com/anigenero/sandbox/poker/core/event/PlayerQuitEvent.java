package com.anigenero.sandbox.poker.core.event;

import com.anigenero.sandbox.poker.core.constant.PlayEvent;

public class PlayerQuitEvent extends PokerEvent<String> {

    public PlayerQuitEvent(String user) {
        super(user, user);
    }

    @Override
    public PlayEvent getEvent() {
        return PlayEvent.PLAYER_QUIT;
    }

}
