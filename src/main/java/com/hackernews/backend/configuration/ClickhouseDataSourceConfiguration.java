package com.hackernews.backend.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.hackernews.backend.model.dao.clickhouse",
        entityManagerFactoryRef = "clickhouseEntityManagerFactory",
        transactionManagerRef = "clickhouseTransactionManager"
)
public class ClickhouseDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.clickhouse")
    public DataSourceProperties clickhouseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "clickhouseDataSource")
    public DataSource clickhouseDataSource() {
        return clickhouseDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "clickhouseJdbcTemplate")
    public JdbcTemplate clickhouseJdbcTemplate(@Qualifier("clickhouseDataSource") DataSource clickhouseDataSource) {
        return new JdbcTemplate(clickhouseDataSource);
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean clickhouseEntityManagerFactory(
            @Qualifier("clickhouseDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public PlatformTransactionManager clickhouseTransactionManager(
            @Qualifier("clickhouseEntityManagerFactory") LocalContainerEntityManagerFactoryBean clickhouseEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(clickhouseEntityManagerFactory.getObject()));
    }
}
