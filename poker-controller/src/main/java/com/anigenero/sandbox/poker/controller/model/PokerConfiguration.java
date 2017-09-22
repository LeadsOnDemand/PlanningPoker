package com.anigenero.sandbox.poker.controller.model;

import java.io.Serializable;
import java.util.List;

public class PokerConfiguration implements Serializable {

    private List<PokerCardDTO> cardDeck;

    public void setCardDeck(List<PokerCardDTO> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public List<PokerCardDTO> getCardDeck() {
        return cardDeck;
    }

}
