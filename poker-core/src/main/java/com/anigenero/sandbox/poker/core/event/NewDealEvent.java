package com.anigenero.sandbox.poker.core.event;

import com.anigenero.sandbox.poker.core.constant.PlayEvent;

public class NewDealEvent extends PokerEvent<String> {

    public NewDealEvent(String user, String name) {
        super(user, name);
    }

    @Override
    public PlayEvent getEvent() {
        return PlayEvent.NEW_DEAL;
    }

}
