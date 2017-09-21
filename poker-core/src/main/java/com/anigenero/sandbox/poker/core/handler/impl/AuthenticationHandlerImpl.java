package com.anigenero.sandbox.poker.core.handler.impl;

import com.anigenero.sandbox.poker.controller.handler.AuthenticationHandler;
import com.anigenero.sandbox.poker.core.model.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class AuthenticationHandlerImpl implements AuthenticationHandler {

    private static final Logger log = LogManager.getLogger(AuthenticationHandler.class);

    private final KeyGenerator keyGenerator;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthenticationHandlerImpl(ObjectMapper objectMapper) throws NoSuchAlgorithmException {
        this.keyGenerator = KeyGenerator.getInstance("AES");
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean isSessionValid(String token) {

        try {

            // Validate the token
            Key key = keyGenerator.generateKey();
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);

            return true;

        } catch (Exception e) {
            log.warn("Invalid token requested: {}", token);
            return false;
        }

    }

    /**
     * Issues a
     *
     * @param sessionId {@link String}
     * @param username  {@link String}
     * @return {@link String}
     * @throws JsonProcessingException
     */
    String issueToken(final String sessionId, final String username) throws Exception {

        return Jwts.builder()
                .setSubject(this.objectMapper.writeValueAsString(new UserSession(sessionId, username)))
                .setIssuedAt(new Date())
                .setExpiration(this.toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, keyGenerator.generateKey())
                .compact();

    }

    /**
     * Converts a {@link LocalDateTime} into a {@link Date}
     *
     * @param localDateTime {@link LocalDateTime}
     * @return {@link Date}
     */
    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
