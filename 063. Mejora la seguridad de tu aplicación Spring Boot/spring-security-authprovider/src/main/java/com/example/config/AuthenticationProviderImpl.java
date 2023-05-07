package com.example.config;

import com.example.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserDetailsService service;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private HttpServletRequest request;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        UserDetails user = service.loadUserByUsername(username);
        String password = user.getPassword();

        if(!isValidPassword(rawPassword, password))
            throw new BadCredentialsException("");

//        var authDetails = (WebAuthenticationDetails)authentication;
//        String ip = authDetails.getRemoteAddress();

        // Personalización y mejoras de la seguridad:
        // Ejemplo: Comprobar la IP
        String ip = request.getRemoteAddr();
        if(!isValidIp(ip, user))
            throw new BadCredentialsException("");

        return new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities());
    }

    /*
    Si la ip del usuario no es la misma que la ip con la que se registró
    entonces no puede acceder.

    TODO : Si la ip es diferente, entonces se debe enviar un correo al usuario para
    permitir esa ip solo si el usuario acepta
     */
    private boolean isValidIp(String ip, UserDetails user) {
        System.out.println("checking ip: " + ip);
        return ((UserEntity) user).getIps().contains(ip);
    }

    private boolean isValidPassword(String rawPassword, String password) {
        return encoder.matches(rawPassword, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
