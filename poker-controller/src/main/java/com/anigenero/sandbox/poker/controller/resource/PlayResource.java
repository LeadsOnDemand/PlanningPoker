package com.anigenero.sandbox.poker.controller.resource;

import com.anigenero.sandbox.poker.controller.bean.DefaultResponse;
import com.anigenero.sandbox.poker.controller.handler.PlayPokerHandler;
import com.anigenero.sandbox.poker.controller.model.PokerEstimateDTO;
import com.anigenero.sandbox.poker.controller.resource.security.AllowAnonymous;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Controller
@Path("/play")
@Produces(MediaType.APPLICATION_JSON)
public class PlayResource {

    private static final Logger log = LogManager.getLogger(PlayResource.class);

    private final PlayPokerHandler playPokerHandler;

    @Autowired
    public PlayResource(PlayPokerHandler playPokerHandler) {
        this.playPokerHandler = playPokerHandler;
    }

    @AllowAnonymous
    @Path("/available")
    @GET
    public DefaultResponse<Boolean> isSessionAvailable() {

        log.info("Checking if a session is available");
        return new DefaultResponse<>(this.playPokerHandler.isSlotAvailable());

    }

    @Path("/new/{name}")
    @POST
    public DefaultResponse<Boolean> newRound(@PathParam("name") final String taskName, @Context HttpServletRequest servletRequest) {
        this.playPokerHandler.startNewRound(taskName, servletRequest);
        return new DefaultResponse<>(true);
    }

    @Path("/submit")
    @GET
    public DefaultResponse<Boolean> submitEstimate(PokerEstimateDTO estimate, @Context HttpServletRequest servletRequest) {
        this.playPokerHandler.submitEstimate(estimate, servletRequest);
        return new DefaultResponse<>(true);
    }

}
