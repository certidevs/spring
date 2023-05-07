package com.example;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        DataSource datasource = ctx.getBean(DataSource.class);

        Flyway flyway = Flyway.configure().dataSource(datasource)
                .locations("db/migration", "db/callbacks")
                .callbacks(new FlywayCallback())
                .load();

        Arrays.stream(flyway.info().all()).forEach(migrations -> {
            System.out.println("Version: " + migrations.getVersion().getVersion());
        });

    }

}
