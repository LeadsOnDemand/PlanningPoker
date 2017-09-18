package com.anigenero.sandbox.poker.controller.resource.mapper;

import com.anigenero.sandbox.poker.common.exception.ServerException;
import com.anigenero.sandbox.poker.controller.bean.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerExceptionMapper implements ExceptionMapper<ServerException> {

    private static final Logger log = LogManager.getLogger(ServerExceptionMapper.class);

    @Override
    public Response toResponse(ServerException exception) {

        log.error("Server error occurred", exception);

        ErrorResponse errorResponse = new ErrorResponse(exception.getError());
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(errorResponse).build();

    }

}
