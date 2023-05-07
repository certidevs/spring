package com.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // http://localhost:8081/hello
    @GetMapping("/hello")
    public String hello(
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OAuth2User principal) {

        System.out.println("client = " + client.getClientRegistration().getClientName());
        System.out.println("principal name = " + principal.getName());
        System.out.println("principal attrs = " + principal.getAttributes());


        return "Hello from app2 " + principal.getName();

    }
}
