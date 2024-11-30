package com.hackernews.backend.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ClickhouseDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.clickhouse")
    public DataSourceProperties clickhouseDataSourceProperties() {
        return new DataSourceProperties();
    }

    //@Primary
    @Bean(name = "clickhouseDataSource")
    public DataSource clickhouseDataSource() {
        return clickhouseDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "clickhouseJdbcTemplate")
    public JdbcTemplate clickhouseJdbcTemplate(@Qualifier("clickhouseDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
