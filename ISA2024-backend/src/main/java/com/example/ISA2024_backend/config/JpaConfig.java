package com.example.ISA2024_backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.ISA2024_backend.repository")  // Package where your repositories are located
public class JpaConfig {


    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); 
        dataSource.setUrl("jdbc:postgresql://localhost:5432/ISA2024DB");
        dataSource.setUsername("postgres"); 
        dataSource.setPassword("0000"); 
        return dataSource;
    }

    // EntityManagerFactory configuration
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.example.ISA2024_backend.model");  // Package where your JPA entities are located
        factoryBean.setJpaVendorAdapter(new org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());  // Set any Hibernate properties here
        return factoryBean;
    }

    // Additional Hibernate properties (optional)
    private java.util.Properties hibernateProperties() {
        java.util.Properties properties = new java.util.Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");  // MySQL dialect; change if needed
        properties.setProperty("hibernate.hbm2ddl.auto", "update");  // Update schema automatically
        properties.setProperty("hibernate.show_sql", "true");  // Show SQL in logs
        properties.setProperty("hibernate.format_sql", "true");  // Format SQL in logs
        return properties;
    }

    // JpaTransactionManager configuration
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}