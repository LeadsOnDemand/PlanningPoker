package com.anigenero.sandbox.poker.controller.resource.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestFilter implements Filter {

    private static final Logger log = LogManager.getLogger(RequestFilter.class);

    private static final Long ERROR_REQUEST_TIME_MS = 5000L;
    private static final Long WARN_REQUEST_TIME_MS = 1000L;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // get the URI we are accessing
        final String uri = ((HttpServletRequest) request).getRequestURI();

        // get the start time of the request
        final long startTime = System.currentTimeMillis();

        try {
            chain.doFilter(request, response);
        } finally {

            // determine the amount of time the request took
            final long endTime = System.currentTimeMillis();
            final long totalTime = endTime - startTime;

            // log out the request time for debugging purposes
            if (totalTime > ERROR_REQUEST_TIME_MS) {
                log.error("Long request time: {} ({}ms)", uri, totalTime);
            } else if (totalTime > WARN_REQUEST_TIME_MS) {
                log.warn("Request time: {} ({}ms)", uri, totalTime);
            } else {
                log.debug("Request time: {} ({}ms)", uri, totalTime);
            }

        }

    }

    @Override
    public void destroy() {
        // stub
    }

}