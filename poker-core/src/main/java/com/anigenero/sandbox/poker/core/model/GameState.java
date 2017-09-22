package com.anigenero.sandbox.poker.core.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class GameState implements Serializable {

    private Set<Player> players = new HashSet<>();

    private String taskName;

    private boolean newDealReady;
    private boolean showCards;

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isNewDealReady() {
        return newDealReady;
    }

    public void setNewDealReady(boolean newDealReady) {
        this.newDealReady = newDealReady;
    }

    public boolean isShowCards() {
        return showCards;
    }

    public void setShowCards(boolean showCards) {
        this.showCards = showCards;
    }

}
