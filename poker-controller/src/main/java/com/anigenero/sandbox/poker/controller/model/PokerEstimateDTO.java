package com.anigenero.sandbox.poker.controller.model;

import java.io.Serializable;

public class PokerEstimateDTO implements Serializable {

    private PokerCardDTO value;

    public PokerCardDTO getValue() {
        return value;
    }

    public void setValue(PokerCardDTO value) {
        this.value = value;
    }

}
