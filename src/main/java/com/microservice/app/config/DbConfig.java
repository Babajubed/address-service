package com.microservice.app.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DbConfig {

    @Value("${app.userName}")
    private String postgresUserName;

    @Value("${app.userPassWord}")
    private String postgresPassword;

    @Value("${app.userUrl}")
    private String postgresUrl;
    @ConfigurationProperties(prefix="spring.datasource")
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(postgresUrl)
                .username(postgresUserName)
                .password(postgresPassword)
                .build();
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean managerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.microservice.app.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        emf.setJpaProperties(properties);
        emf.afterPropertiesSet();
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
