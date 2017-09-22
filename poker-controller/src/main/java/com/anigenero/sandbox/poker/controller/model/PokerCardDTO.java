package com.anigenero.sandbox.poker.controller.model;

import java.io.Serializable;

public class PokerCardDTO implements Serializable {

    private Short id;

    private Double value;
    private String symbol;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
