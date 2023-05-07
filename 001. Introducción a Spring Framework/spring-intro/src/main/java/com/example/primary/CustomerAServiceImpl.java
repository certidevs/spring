package com.example.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


//@Service
public class CustomerAServiceImpl implements CustomerService{
    @Override
    public String hello() {
        return "Hello from customer A";
    }
}
