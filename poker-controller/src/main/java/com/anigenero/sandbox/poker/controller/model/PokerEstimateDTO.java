package com.anigenero.sandbox.poker.controller.model;

import java.io.Serializable;

public class PokerEstimateDTO implements Serializable {

    private Integer value;
    private Short storyId;

    public Short getStoryId() {
        return storyId;
    }

    public void setStoryId(Short storyId) {
        this.storyId = storyId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
