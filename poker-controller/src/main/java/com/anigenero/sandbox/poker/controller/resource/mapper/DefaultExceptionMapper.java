package com.anigenero.sandbox.poker.controller.resource.mapper;

import com.anigenero.sandbox.poker.common.exception.ErrorCode;
import com.anigenero.sandbox.poker.controller.bean.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger log = LogManager.getLogger(DefaultExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {

        log.error("Uncaught exception: ", exception.getMessage(), exception);

        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorResponse(ErrorCode.Codes.UNKNOWN))
                .build();

    }

}
