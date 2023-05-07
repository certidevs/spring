package com.example;

import com.example.model.Customer;
import com.example.model.Vehicle;
import com.example.repository.CustomerRepository;
import com.example.repository.VehicleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        var customerRepo = ctx.getBean(CustomerRepository.class);
        var vehicleRepo = ctx.getBean(VehicleRepository.class);

        Customer cust1 = new Customer("cust1", "cust1@gmail.com");
        Customer cust2 = new Customer("cust2", "cust2@gmail.com");
        Customer cust3 = new Customer("cust3", "cust3@gmail.com");
        Customer cust4 = new Customer("cust4", "cust4@gmail.com");
        customerRepo.saveAll(List.of(cust1, cust2, cust3, cust4));

        Vehicle veh1 = new Vehicle("veh1", 1.2, 1990, cust1);
        Vehicle veh2 = new Vehicle("veh2", 1.2, 1991, cust1);
        Vehicle veh3 = new Vehicle("veh3", 1.2, 1990, cust2);
        Vehicle veh4 = new Vehicle("veh4", null, 1990, cust2);
        Vehicle veh5 = new Vehicle("veh5", 1.2, 1990, cust3);
        Vehicle veh6 = new Vehicle("veh6", 1.2, 1990, cust3);
        vehicleRepo.saveAll(List.of(veh1, veh2, veh3, veh4, veh5, veh6));



    }

}
