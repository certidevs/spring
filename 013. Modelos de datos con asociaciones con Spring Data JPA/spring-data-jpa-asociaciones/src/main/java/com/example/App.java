package com.example;

import com.example.model.Address;
import com.example.model.Employee;
import com.example.model.EmployeeType;
import com.example.repository.AddressRepository;
import com.example.repository.CompanyRepository;
import com.example.repository.EmployeeRepository;
import com.example.repository.ProjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

        var addressRepository = context.getBean(AddressRepository.class);
        var employeeRepository = context.getBean(EmployeeRepository.class);
        var projectRepository = context.getBean(ProjectRepository.class);
        var companyRepository = context.getBean(CompanyRepository.class);

        Address address = new Address(null, "Calle falsa", "28908", null);
        addressRepository.save(address);

        Employee employee = new Employee(null, "emp1", 20, true, 40000.0,
                Instant.now(), LocalDate.now(), LocalDateTime.now(),
                List.of("email1", "email2"), EmployeeType.JUNIOR, address, null, null);
        employeeRepository.save(employee);


    }

}
