package com.anigenero.sandbox.poker.core.model;

import com.anigenero.sandbox.poker.controller.model.PokerCardDTO;
import com.anigenero.sandbox.poker.controller.model.PokerEstimateDTO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PokerTask implements Serializable {

    private HashMap<String, PokerCardDTO> estimates = new HashMap<>();

    private String name;

    public HashMap<String, PokerCardDTO> getEstimates() {
        return estimates;
    }

    public void setEstimates(HashMap<String, PokerCardDTO> estimates) {
        this.estimates = estimates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
