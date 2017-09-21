package com.anigenero.sandbox.poker.core.event;

import com.anigenero.sandbox.poker.core.constant.PlayEvent;

import java.io.Serializable;

public abstract class PokerEvent<T extends Serializable> implements Serializable {

    private final String user;
    private final T data;

    public PokerEvent(String user, T data) {
        this.data = data;
        this.user = user;
    }

    public abstract PlayEvent getEvent();

    public final String getUser() {
        return user;
    }

    public final T getData() {
        return data;
    }

}
