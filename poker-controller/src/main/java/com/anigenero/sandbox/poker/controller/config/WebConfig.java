package com.anigenero.sandbox.poker.controller.config;

import com.anigenero.sandbox.poker.controller.resource.filter.CORSFilter;
import com.anigenero.sandbox.poker.controller.resource.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Configuration
@EnableWebSocket
public class WebConfig {

    @Bean
    public FilterRegistrationBean requestFilter() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("request");

        RequestFilter greetingFilter = new RequestFilter();
        registrationBean.setFilter(greetingFilter);
        registrationBean.setOrder(2);

        return registrationBean;

    }

    @Bean
    public FilterRegistrationBean corsFilter() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("cors");

        CORSFilter CORSFilter = new CORSFilter();
        registrationBean.setFilter(CORSFilter);
        registrationBean.setOrder(1);

        return registrationBean;

    }



}
