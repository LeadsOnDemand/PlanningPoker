package com.anigenero.sandbox.poker.controller.socket;

import com.anigenero.sandbox.poker.controller.resource.provider.ObjectMapperResolverProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WebSocketEncoder implements Encoder.Text<Object> {

    private static final Logger log = LogManager.getLogger(WebSocketEncoder.class);

    private ObjectMapper objectMapper;

    @Override
    public String encode(Object object) throws EncodeException {

        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {

            log.error("Could not encode the web socket message", e);
            throw new EncodeException(object, "Could not encode", e);

        }

    }

    @Override
    public void init(EndpointConfig config) {
        this.objectMapper = (new ObjectMapperResolverProvider()).getContext(Object.class);
    }

    @Override
    public void destroy() { }

}
