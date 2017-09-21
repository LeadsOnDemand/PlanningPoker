package com.anigenero.sandbox.poker.core.event;

import com.anigenero.sandbox.poker.core.constant.PlayEvent;

public class SessionCreatedEvent extends PokerEvent<String> {

    public SessionCreatedEvent(String user, String token) {
        super(user, token);
    }

    @Override
    public PlayEvent getEvent() {
        return PlayEvent.SESSION_STARTED;
    }

}
