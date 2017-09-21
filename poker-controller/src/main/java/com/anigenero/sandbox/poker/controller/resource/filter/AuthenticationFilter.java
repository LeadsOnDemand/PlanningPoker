package com.anigenero.sandbox.poker.controller.resource.filter;

import com.anigenero.sandbox.poker.controller.handler.AuthenticationHandler;
import com.anigenero.sandbox.poker.controller.resource.security.AllowAnonymous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Component
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTH_HEADER = "auth";

    private final AuthenticationHandler authenticationHandler;

    @Context
    private ResourceInfo resourceInfo;

    @Autowired
    public AuthenticationFilter(AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // if we're allowing anonymous access, we really don't care about authentication, do we?
        if (resourceInfo.getResourceClass().getAnnotation(AllowAnonymous.class) != null ||
                resourceInfo.getResourceMethod().getAnnotation(AllowAnonymous.class) != null) {
            return;
        }

        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(AUTH_HEADER);

        final String token = authorizationHeader.substring("Bearer".length()).trim();
        if (!this.authenticationHandler.isSessionValid(token)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }

}
