package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {

    /**
     * Method use to configure the sql database
     */

    @Bean
    public JdbcTemplate jdbcTemplate(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/ecommerce");
        dataSource.setPassword("root@123");
        dataSource.setUsername("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return new JdbcTemplate(dataSource);
    }
}
