package com.example.controller;

import com.example.model.CustomerView;
import com.example.model.Role;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import java.util.Collection;

@RestControllerAdvice
public class SecurityAdvice extends AbstractMappingJacksonResponseBodyAdvice {


    @Override
    protected void beforeBodyWriteInternal(
            MappingJacksonValue bodyContainer,
            MediaType contentType,
            MethodParameter returnType,
            ServerHttpRequest request,
            ServerHttpResponse response) {

        if(SecurityContextHolder.getContext().getAuthentication() == null) return;
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities() == null) return;

        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext()
                        .getAuthentication().getAuthorities();

        // obtiene el jsonview para el rol de usuario actual
        Class jsonView = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(Role::valueOf)
                .map(CustomerView.ROLE_VIEWS::get)
                .findFirst().orElseThrow(); // Asumimos que solo hay una JsonView por cada role

        bodyContainer.setSerializationView(jsonView);

    }
}
