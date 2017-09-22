package com.anigenero.sandbox.poker.core.handler.impl;

import com.anigenero.sandbox.poker.controller.handler.ConfigurationHandler;
import com.anigenero.sandbox.poker.controller.model.PokerConfiguration;
import com.anigenero.sandbox.poker.core.mapper.PokerCardDTOMapper;
import com.anigenero.sandbox.poker.dao.PokerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationHandlerImpl implements ConfigurationHandler{

    private final PokerCardDTOMapper pokerCardDTOMapper;
    private final PokerCardService pokerCardService;

    @Autowired
    public ConfigurationHandlerImpl(PokerCardDTOMapper pokerCardDTOMapper, PokerCardService pokerCardService) {
        this.pokerCardDTOMapper = pokerCardDTOMapper;
        this.pokerCardService = pokerCardService;
    }

    @Override
    public PokerConfiguration getConfiguration() {

        PokerConfiguration configuration = new PokerConfiguration();
        configuration.setCardDeck(this.pokerCardDTOMapper.toPokerCardDTOList(pokerCardService.getPokerCards()));

        return configuration;

    }

}
