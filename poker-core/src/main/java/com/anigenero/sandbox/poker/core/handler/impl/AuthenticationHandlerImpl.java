package com.anigenero.sandbox.poker.core.handler.impl;

import com.anigenero.sandbox.poker.common.constants.HeaderConstants;
import com.anigenero.sandbox.poker.controller.handler.AuthenticationHandler;
import com.anigenero.sandbox.poker.core.model.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthenticationHandlerImpl implements AuthenticationHandler {

    private static final Logger log = LogManager.getLogger(AuthenticationHandler.class);

    private final Key key;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthenticationHandlerImpl(ObjectMapper objectMapper) throws NoSuchAlgorithmException {
        this.key = KeyGenerator.getInstance("AES").generateKey();
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean isSessionValid(String token) {

        try {

            // Validate the token
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;

        } catch (Exception e) {
            log.warn("Invalid token requested: {}", token);
            return false;
        }

    }

    /**
     * Gets the user session from the JWT token on the {@link HttpServletRequest}
     *
     * @param servletRequest {@link HttpServletRequest}
     * @return {@link UserSession}
     */
    UserSession getUserSession(HttpServletRequest servletRequest) {

        final String token = servletRequest.getHeader(HeaderConstants.AUTH_HEADER);

        try {

            // Validate the token
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return this.objectMapper.readValue(claims.getSubject(), UserSession.class);

        } catch (Exception e) {
            log.warn("Invalid token requested: {}", token);
            return null;
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
                .signWith(SignatureAlgorithm.HS256, key)
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
