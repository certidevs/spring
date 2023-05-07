package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class CustomerView {

    public static class CustomerUser {}
    public static class CustomerManager extends CustomerUser{}
    public static class CustomerAdmin extends CustomerManager{}

    public static final Map<Role, Class> ROLE_VIEWS = new HashMap<>();
    static {
        ROLE_VIEWS.put(Role.ROLE_ADMIN, CustomerAdmin.class);
        ROLE_VIEWS.put(Role.ROLE_MANAGER, CustomerManager.class);
        ROLE_VIEWS.put(Role.ROLE_USER, CustomerUser.class);
    }
}
