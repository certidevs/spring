package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Server1Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext ctx =
                SpringApplication.run(Server1Application.class, args);

        var client = ctx.getBean(Server2Client.class);
        System.out.println(client.getHelloFromServer2());
    }

}
