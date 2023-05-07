package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        var service = ctx.getBean(MailService.class);

        service.sendEmailFromTemplate("user1@example.com", "mail/hello", "Asunto 1");
        service.sendEmailFromTemplate("user2@example.com", "mail/bye", "Asunto 2");
        service.sendEmail("user2@example.com", "Asunto 3", "Contenido hola mundo", false, false);


    }

}
