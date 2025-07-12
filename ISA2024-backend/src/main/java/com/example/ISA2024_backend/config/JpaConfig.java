package com.example.ISA2024_backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "repository")  // Package where your repositories are located
public class JpaConfig {

   
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); 
        dataSource.setUrl("jdbc:postgresql://localhost:5432/ISA2024DB");
        dataSource.setUsername("postgres"); 
        dataSource.setPassword("0000"); 
        return dataSource;
    }

    // EntityManagerFactory configuration
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.example.model");  // Package where your JPA entities are located
        factoryBean.setJpaVendorAdapter(new org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());  // Set any Hibernate properties here
        return factoryBean;
    }

    // Additional Hibernate properties (optional)
    private java.util.Properties hibernateProperties() {
        java.util.Properties properties = new java.util.Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");  // MySQL dialect; change if needed
        properties.setProperty("hibernate.hbm2ddl.auto", "update");  // Update schema automatically
        properties.setProperty("hibernate.show_sql", "true");  // Show SQL in logs
        properties.setProperty("hibernate.format_sql", "true");  // Format SQL in logs
        return properties;
    }

    // JpaTransactionManager configuration
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}