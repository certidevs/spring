package com.example.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @PreAuthorize("hasAuthority('read')")
    public String method1(){
        return "method1";
    }

    @PreAuthorize("hasAnyAuthority('read', 'write')")
    public String method2(){
        return "method2";
    }

    @PreAuthorize("hasAuthority('write')")
    public String method3(){
        return "method3";
    }



}
