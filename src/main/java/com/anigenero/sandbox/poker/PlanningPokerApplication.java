package com.anigenero.sandbox.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class PlanningPokerApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    /**
     * Entrance method for Spring Boot
     *
     * @param args {@link String}
     */
    public static void main(String[] args) {
        SpringApplication.run(PlanningPokerApplication.class, args);
    }

}
