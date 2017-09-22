package com.anigenero.sandbox.poker.controller.handler;

import com.anigenero.sandbox.poker.controller.model.PokerConfiguration;

public interface ConfigurationHandler {

    /**
     * Gets the poker configuration from the system
     *
     * @return {@link PokerConfiguration}
     */
    PokerConfiguration getConfiguration();

}
