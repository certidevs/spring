package com.example.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// H2
@Configuration
@EnableJpaRepositories(basePackages = "com.example.datasource1",
entityManagerFactoryRef = "entityManagerFactory1",
transactionManagerRef = "transactionManager1")
public class DataSource1Config {

    @Bean(name = "dataSource1")
    public DataSource datasource1(){
        return DataSourceBuilder.create().driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;")
                .username("sa")
                .password("")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory1(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource1") DataSource datasource1) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        var em = builder.dataSource(datasource1)
                .packages("com.example.datasource1")
                .persistenceUnit("persistenceUnit1")
                .build();
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager1(
            @Qualifier("entityManagerFactory1") LocalContainerEntityManagerFactoryBean entityManagerFactory1) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory1.getObject()));
    }

}
