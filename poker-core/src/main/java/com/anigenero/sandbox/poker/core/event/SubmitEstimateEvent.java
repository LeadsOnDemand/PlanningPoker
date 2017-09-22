package com.anigenero.sandbox.poker.core.event;

import com.anigenero.sandbox.poker.controller.model.PokerCardDTO;
import com.anigenero.sandbox.poker.controller.model.PokerEstimateDTO;
import com.anigenero.sandbox.poker.core.constant.PlayEvent;

import java.util.HashMap;
import java.util.Map;

public class SubmitEstimateEvent extends PokerEvent<HashMap<String, PokerCardDTO>> {

    public SubmitEstimateEvent(String user, HashMap<String, PokerCardDTO> data) {
        super(user, data);
    }

    @Override
    public PlayEvent getEvent() {
        return PlayEvent.ESTIMATE_SUBMITTED;
    }

}
