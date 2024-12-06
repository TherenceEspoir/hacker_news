package com.hackernews.backend.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.hackernews.backend.model.dao.postgres",
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager"
)
public class PostgresDataSourceConfiguration {

    /**
     * Provides configuration properties for the PostgreSQL data source.
     *
     * @return an instance of {@link DataSourceProperties} configured with the PostgreSQL-specific properties
     */
    @Bean
    @ConfigurationProperties("spring.datasource.postgres")
    public DataSourceProperties postgresDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Creates and configures the primary DataSource bean for a PostgreSQL database using
     * properties defined under the "spring.datasource.postgres" configuration prefix.
     *
     * @return a DataSource object configured for PostgreSQL database access
     */
    @Primary
    @Bean(name = "postgresDataSource")
    public DataSource postgresDataSource() {
        return postgresDataSourceProperties().initializeDataSourceBuilder().build();
    }

    /**
     * Configures and returns a {@link LocalContainerEntityManagerFactoryBean} for the PostgreSQL data source.
     *
     * @param dataSource the PostgreSQL data source
     * @param builder the builder for creating entity manager factory instances
     * @return a configured {@link LocalContainerEntityManagerFactoryBean}
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            @Qualifier("postgresDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.hackernews.backend.model.entity")
                .build();
    }

    /**
     * Configures a transaction manager for PostgreSQL using the provided EntityManagerFactory.
     *
     * @param entityManagerFactory the EntityManagerFactory used to create the transaction manager
     * @return a PlatformTransactionManager for managing transactions with the PostgreSQL database
     */
    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}