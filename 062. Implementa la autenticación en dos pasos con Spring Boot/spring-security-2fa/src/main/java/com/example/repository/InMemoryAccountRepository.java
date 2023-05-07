package com.example.repository;

import com.example.model.Account;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class InMemoryAccountRepository implements AccountRepository {

    private static String ADMIN_SECRET = "6YFX5TVT76OHHNMS";

    private List<Account> accounts;

    private PasswordEncoder encoder;

    public InMemoryAccountRepository(PasswordEncoder encoder) {
        this.encoder = encoder;
        accounts = new ArrayList<Account>();
        init();
    }

    private void init() {
        Account admin = new Account();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("admin"));
        admin.setAuth2FA(true);
        admin.setSecret(ADMIN_SECRET);
        admin.setRoles(Arrays.asList("ROLE_USER"));

        Account user = new Account();
        user.setUsername("user");
        user.setPassword(encoder.encode("user"));
        user.setAuth2FA(false);
        user.setRoles(Arrays.asList("ROLE_USER"));

        accounts.add(admin);
        accounts.add(user);
    }

    @Override
    public Account find(String username) {
        return accounts.stream()
                .filter(account -> account.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
