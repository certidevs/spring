package com.example.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// MYSQL
@Configuration
@EnableJpaRepositories(basePackages = "com.example.datasource2",
entityManagerFactoryRef = "entityManagerFactory2",
transactionManagerRef = "transactionManager2")
public class DataSource2Config {

    @Bean(name = "dataSource2")
    @Primary
    public DataSource datasource2(){
        return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/spring_data_jpa_mysql?createDatabaseIfNotExist=true")
                .username("root")
                .password("admin")
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource2") DataSource datasource2) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        var em = builder.dataSource(datasource2)
                .packages("com.example.datasource2")
                .persistenceUnit("persistenceUnit2")
                .build();
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager2(
            @Qualifier("entityManagerFactory2")
            LocalContainerEntityManagerFactoryBean entityManagerFactory2) {
        return new JpaTransactionManager(
                Objects.requireNonNull(entityManagerFactory2.getObject()));
    }

}
