package com.anigenero.sandbox.poker.core.event;

import com.anigenero.sandbox.poker.controller.model.PokerEstimateDTO;
import com.anigenero.sandbox.poker.core.constant.PlayEvent;

public class SubmitEstimateEvent extends PokerEvent<PokerEstimateDTO> {

    public SubmitEstimateEvent(String user, PokerEstimateDTO data) {
        super(user, data);
    }

    @Override
    public PlayEvent getEvent() {
        return PlayEvent.ESTIMATE_SUBMITTED;
    }

}
