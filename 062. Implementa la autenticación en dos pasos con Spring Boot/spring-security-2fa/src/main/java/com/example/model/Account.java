package com.example.model;

import java.util.List;

public class Account {

    private String username;
    private String password;
    private String secret;
    private Boolean auth2FA;
    private List<String> roles;

    public Account() {
    }

    public Account(String username, String password, String secret, Boolean auth2FA, List<String> roles) {
        this.username = username;
        this.password = password;
        this.secret = secret;
        this.auth2FA = auth2FA;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Boolean getAuth2FA() {
        return auth2FA;
    }

    public void setAuth2FA(Boolean auth2FA) {
        this.auth2FA = auth2FA;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
