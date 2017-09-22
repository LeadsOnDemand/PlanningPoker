package com.anigenero.sandbox.poker.dao.config;

import com.anigenero.sandbox.poker.common.util.PropertyUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    private static final String DB_URL = "db.url";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_DRIVER = "db.driver";

    private static final int MIN_POOL_SIZE = 50;
    private static final int MAX_POOL_SIZE = 1000;

    @Primary
    @Bean(name = "platformDataSource")
    public DataSource platformDataSource(PropertyUtil propertyUtil) {

        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(propertyUtil.getProperty(DB_DRIVER));
        dataSource.setJdbcUrl(propertyUtil.getProperty(DB_URL));
        dataSource.setUsername(propertyUtil.getProperty(DB_USERNAME));
        dataSource.setPassword(propertyUtil.getProperty(DB_PASSWORD));
        dataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        dataSource.setMinimumIdle(MIN_POOL_SIZE);

        return dataSource;

    }

}
