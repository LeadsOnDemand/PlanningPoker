package com.anigenero.sandbox.poker.core.event;

import com.anigenero.sandbox.poker.core.constant.PlayEvent;

public class RevealCardsEvent extends PokerEvent<String> {

    public RevealCardsEvent(String user) {
        super(user, null);
    }

    @Override
    public PlayEvent getEvent() {
        return PlayEvent.SHOW_CARDS;
    }

}
