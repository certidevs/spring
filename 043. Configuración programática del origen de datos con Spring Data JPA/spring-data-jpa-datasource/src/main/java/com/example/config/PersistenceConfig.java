package com.example.config;

import com.example.App;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class PersistenceConfig {


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, DataSource dataSource) {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "create");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
//        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        props.put("hibernate.session.events.log.LOG_QUERIES_SLOWER_THAN_MS", "1");

        var em = builder.dataSource(dataSource).packages(App.class.getPackage().getName()).build();
        em.setJpaPropertyMap(props);
        return em;
    }
    // H2 en memoria RAM
//    @Bean
//    public DataSource dataSource(){
//        return DataSourceBuilder.create()
//                .driverClassName("org.h2.Driver")
//                .url("jdbc:h2:mem:test")
//                .username("sa")
//                .password("")
//                .build();
//    }

    // H2 en disco
//    @Bean
//    public DataSource dataSource(){
//        return DataSourceBuilder.create()
//                .driverClassName("org.h2.Driver")
//                .url("jdbc:h2:file:./db/database;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;MODE=MYSQL;DATABASE_TO_UPPER=FALSE;")
//                .username("sa")
//                .password("")
//                .build();
//    }

    // MySQL en localhost
    @Bean
    public DataSource dataSource(){

        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/spring_data_jpa_datasource?createDatabaseIfNotExist=true")
                .username("root")
                .password("admin")
                .build();
    }

    // PostgreSQL en localhost
//    @Bean
//    public DataSource dataSource(){
//
//        return DataSourceBuilder.create()
//                .driverClassName("org.postgresql.Driver")
//                .url("jdbc:postgresql://localhost:5432/spring_data_jpa_datasource")
//                .username("postgres")
//                .password("admin")
//                .build();
//    }

}
