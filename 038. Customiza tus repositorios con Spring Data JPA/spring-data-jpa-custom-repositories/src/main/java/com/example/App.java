package com.example;

import com.example.example1.ProductRepository;
import com.example.example2.BaseUserRepository;
import com.example.example2.EmployeeRepository;
import com.example.example2.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

        var productRepo = context.getBean(ProductRepository.class);
        productRepo.findAllWithCriteria();
        productRepo.fetchStats();
        productRepo.findAllByName("prod1");


        var personRepo = context.getBean(PersonRepository.class);
        personRepo.findByEmail("person@gmail.com");

        var employeeRepo = context.getBean(EmployeeRepository.class);
        employeeRepo.findByEmail("emp1@company.com");

        // Esto daría error debido a que BaseUserRepository hemos prohibido que sea instanciable
//        var baseRepo = context.getBean(BaseUserRepository.class);
//        baseRepo.findByEmail("emp1@company.com");
    }

}
