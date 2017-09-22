package com.anigenero.sandbox.poker.controller.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/rs")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        // DO NOT DELETE: this prevents Jersey from returning WADL XML on OPTIONS requests
        property(ServerProperties.WADL_FEATURE_DISABLE, true);

        packages(true, "com.anigenero.sandbox.poker.controller.resource");

    }

}