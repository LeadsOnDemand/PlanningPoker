package com.anigenero.sandbox.poker.core.model;

import com.anigenero.sandbox.poker.controller.model.PokerCardDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Player implements Serializable {

    @JsonIgnore
    private String sessionId;

    private PokerCardDTO currentCard;
    private String username;

    private boolean leader;

    public Player() {  }

    public Player(String username, String sessionId, boolean isLeader) {
        this.username = username;
        this.sessionId = sessionId;
        this.leader = isLeader;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public PokerCardDTO getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(PokerCardDTO currentCard) {
        this.currentCard = currentCard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (sessionId != null ? !sessionId.equals(player.sessionId) : player.sessionId != null) return false;
        if (currentCard != null ? !currentCard.equals(player.currentCard) : player.currentCard != null) return false;
        return username != null ? username.equals(player.username) : player.username == null;
    }

    @Override
    public int hashCode() {
        return sessionId.hashCode();
    }

}
