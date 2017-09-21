package com.anigenero.sandbox.poker.controller.config;

import com.anigenero.sandbox.poker.controller.resource.filter.CORSFilter;
import com.anigenero.sandbox.poker.controller.resource.filter.RequestFilter;
import com.anigenero.sandbox.poker.controller.socket.PlayPokerEndpoint;
import com.anigenero.sandbox.poker.controller.socket.config.WebSocketConfigurator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@ConditionalOnWebApplication
@Configuration
@EnableWebSocket
public class WebConfig {

    @Bean
    public WebSocketConfigurator customSpringConfigurator() {
        return new WebSocketConfigurator();
    }

    @Bean
    public ServletContextAware endpointExporterInitializer(final ApplicationContext applicationContext) {
        return servletContext -> {
            ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
            serverEndpointExporter.setApplicationContext(applicationContext);
            try {
                serverEndpointExporter.afterPropertiesSet();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

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
