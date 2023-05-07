package com.example.service;

import com.example.model.Vehicle;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class VehicleService {

    @RolesAllowed("ROLE_ADMIN")
    public String method1(){
        return "method1";
    }

    @Secured("ROLE_ADMIN")
    public String method2(){
        return "method2";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String method3(){
        return "method3";
    }

    @PreFilter("filterObject.owner == authentication.principal.username")
    public List<Vehicle> method4(List<Vehicle> vehicles){
        // operaciones...
        return vehicles;
    }

}
