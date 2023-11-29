package com.YcTechAcademy.travelSchedules.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("live")
public class LiveDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:mysql://localhost:3306/travel_schedules?serverTimezone=Asia/Seoul")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .username("root")
                .password("0000")
                .build();
    }
}