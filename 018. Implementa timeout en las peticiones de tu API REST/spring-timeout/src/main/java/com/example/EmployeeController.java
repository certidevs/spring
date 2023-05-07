package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/employees/{id}")
    public Callable<Employee> findById(@PathVariable Long id){
        return () -> {
            Thread.sleep(8000L); // simular un retardo de tiempo
            return new Employee(id, "", 4000.0);
        };
    }

    @GetMapping("/employees0")
    public List<Employee> findAll0(){
        return service.findAll0();
    }

    @GetMapping("/employees1/prueba1")
    public CompletableFuture<List<Employee>> findAll1(){
        return service.findAll1();
    }

    @GetMapping("/employees1/prueba2")
    public List<Employee> findAll1Block() throws ExecutionException, InterruptedException, TimeoutException {
        // Para aquellos casos en los que no tengamos ya un @TimeLimiter definido
        return service.findAll1().get(3, TimeUnit.SECONDS);
    }

    @GetMapping("/employees1/prueba3")
    public CompletableFuture<List<Employee>> findAll1WhenComplete(){
        return service.findAll1().whenComplete((employees, ex) -> {
            if (employees != null)
                System.out.println(employees);

            if(ex != null)
                System.out.println(ex.getMessage());
        });
    }

    @GetMapping("/employees2")
    public CompletableFuture<List<Employee>> findAll2() throws ExecutionException, InterruptedException, TimeoutException {
        return service.findAll2();
    }

}
