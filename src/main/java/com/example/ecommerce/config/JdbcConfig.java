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
        dataSource.setUrl("jdbc:mysql://localhost:3306/ecommerce_db");    // will be same
        dataSource.setUsername("root");                                // my username of db is root, change it according to yours.
        dataSource.setPassword("Myahya2013");                                // my password of db is root, change it according to yours.
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");    // will be same

        return new JdbcTemplate(dataSource);
    }
}
