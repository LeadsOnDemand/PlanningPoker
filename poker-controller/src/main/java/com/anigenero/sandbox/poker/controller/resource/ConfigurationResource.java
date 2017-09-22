package com.anigenero.sandbox.poker.controller.resource;

import com.anigenero.sandbox.poker.controller.bean.DefaultResponse;
import com.anigenero.sandbox.poker.controller.handler.ConfigurationHandler;
import com.anigenero.sandbox.poker.controller.model.PokerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
@Path("/config")
@Produces(MediaType.APPLICATION_JSON)
public class ConfigurationResource {

    private final ConfigurationHandler configurationHandler;

    @Autowired
    public ConfigurationResource(ConfigurationHandler configurationHandler) {
        this.configurationHandler = configurationHandler;
    }

    @Path("/")
    @GET
    public DefaultResponse<PokerConfiguration> getConfiguration() {
        return new DefaultResponse<>(this.configurationHandler.getConfiguration());
    }

}
