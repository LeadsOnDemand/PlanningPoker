package com.anigenero.sandbox.poker.dao.config;

import com.anigenero.sandbox.poker.dao.config.data.MyBatisAbstractDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(MyBatisAbstractDataSource dataSource) throws Exception {

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.addMappers("com.anigenero.sandbox.poker.dao.mybatis");

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfiguration(configuration);

        return factoryBean.getObject();

    }

}
