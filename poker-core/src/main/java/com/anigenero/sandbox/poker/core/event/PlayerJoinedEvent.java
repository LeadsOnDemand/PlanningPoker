package com.anigenero.sandbox.poker.core.event;

import com.anigenero.sandbox.poker.core.constant.PlayEvent;

public class PlayerJoinedEvent extends PokerEvent<String> {

    public PlayerJoinedEvent(String user) {
        super(user, user);
    }

    @Override
    public PlayEvent getEvent() {
        return PlayEvent.PLAYER_JOINED;
    }

}
