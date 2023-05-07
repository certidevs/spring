package com.example;

import com.example.model.Customer;
import com.example.model.Employee;
import com.example.model.Product;
import com.example.repository.CustomerRepository;
import com.example.repository.EmployeeRepository;
import com.example.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

        var employeeRepo = context.getBean(EmployeeRepository.class);

        // CRUD repository: Create Retrieve Update Delete
        Employee employee1 = new Employee(null, "emp1");
        Employee employee2 = new Employee(null, "emp2");
        Employee employee3 = new Employee(null, "emp3");
        Employee employee4 = new Employee(null, "emp4");
        Employee employee5 = new Employee(null, "emp5");
        Employee employee6 = new Employee(null, "emp6");
        Employee employee7 = new Employee(null, "emp7");
        Employee employee8 = new Employee(null, "emp8");
        Employee employeeDB = employeeRepo.save(employee1);
        employeeRepo.saveAll(List.of(employee2,employee3, employee4, employee5, employee6, employee7, employee8));
        employeeRepo.findById(1L).ifPresent(System.out::println);
        boolean exists = employeeRepo.existsById(1L);

        Iterable<Employee> employees = employeeRepo.findAll();
        Iterator<Employee> it = employees.iterator();
        while(it.hasNext()){
            Employee emp = it.next();
        }

        Iterable<Employee> employeesByIDs =
                employeeRepo.findAllById(List.of(1L, 2L, 3L));

        long totalEmployees = employeeRepo.count();

        employeeRepo.deleteById(1L);

        employeeRepo.delete(employee2);

        employeeRepo.deleteAllById(List.of(3L, 4L, 5L));

        employeeRepo.deleteAll(List.of(employee6, employee7));

        employeeRepo.deleteAll();


        // Paging and sorting repository
        var customerRepo = context.getBean(CustomerRepository.class);
        Customer cust1 = new Customer(null, "cust1");
        Customer cust2 = new Customer(null, "cust2");
        Customer cust3 = new Customer(null, "cust3");
        Customer cust4 = new Customer(null, "cust4");
        customerRepo.saveAll(List.of(cust1, cust2, cust3, cust4));

        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        customerRepo.findAll(sort);

        Pageable pageable = PageRequest.of(0, 3, sort);
        customerRepo.findAll(pageable);

        // Jpa repository
        Product prod1 = new Product(null, "prod1");
        Product prod2 = new Product(null, "prod2");
        Product prod3 = new Product(null, "prod3");

        var productRepo = context.getBean(ProductRepository.class);
        List<Product> prods = productRepo.saveAll(List.of(prod1, prod2, prod3));

        productRepo.findAll();
        productRepo.findAll(Sort.by("name"));
        productRepo.findAllById(List.of(1L, 2L, 3L));
        productRepo.flush();
        productRepo.saveAndFlush(prod3);
        productRepo.saveAllAndFlush(List.of(prod1, prod2));
        productRepo.deleteAllInBatch(List.of(prod1, prod2));
        productRepo.deleteAllByIdInBatch(List.of(1L, 2L));
        productRepo.deleteAllInBatch();
        // no genera una sentencia hasta que se accede
        // a las propiedades del producto
        Product product = productRepo.getReferenceById(1L);
        productRepo.findAll(Example.of(product));
        productRepo.findAll(Example.of(product), sort);



    }

}
