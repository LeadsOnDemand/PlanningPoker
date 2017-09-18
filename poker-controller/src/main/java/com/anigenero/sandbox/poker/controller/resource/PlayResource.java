package com.anigenero.sandbox.poker.controller.resource;

import com.anigenero.sandbox.poker.controller.bean.DefaultResponse;
import com.anigenero.sandbox.poker.controller.handler.PlayPokerHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/play")
@Produces(MediaType.APPLICATION_JSON)
public class PlayResource {

    private static final Logger log = LogManager.getLogger(PlayResource.class);

    private final PlayPokerHandler playPokerHandler;

    @Autowired
    public PlayResource(PlayPokerHandler playPokerHandler) {
        this.playPokerHandler = playPokerHandler;
    }

    @Path("/available")
    @GET
    public DefaultResponse<Boolean> isSessionAvailable() {

        log.info("Checking if a session is available");
        return new DefaultResponse<>(this.playPokerHandler.isSlotAvailable());

    }

}
