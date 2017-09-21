package com.anigenero.sandbox.poker.core.model;

import com.anigenero.sandbox.poker.controller.model.PokerEstimateDTO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PokerTask implements Serializable {

    private Map<String, PokerEstimateDTO> estimates = new HashMap<>();

    private String name;

    public Map<String, PokerEstimateDTO> getEstimates() {
        return estimates;
    }

    public void setEstimates(Map<String, PokerEstimateDTO> estimates) {
        this.estimates = estimates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
