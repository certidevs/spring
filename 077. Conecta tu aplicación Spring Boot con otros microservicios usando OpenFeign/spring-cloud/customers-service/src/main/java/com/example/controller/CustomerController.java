package com.example.controller;

import com.example.dto.DirectionDTO;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customers-service")
public class CustomerController {

    @Autowired
    DiscoveryClient eurekaClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerService service;

    @GetMapping
    public String getCustomer() {
        return "Hola desde customer service";
    }

    @GetMapping("/directions1")
    public String getCustomerWithDirection() {

        System.out.println(eurekaClient.description());
        eurekaClient.getInstances("directions-service").forEach(instance -> {
            System.out.println(instance.getUri());
            System.out.println(instance.getHost());
            System.out.println(instance.getPort());
            System.out.println(instance.getServiceId());
            System.out.println(instance.isSecure());
        });

        String url = eurekaClient.getInstances("directions-service").get(0).getUri().toString();
        System.out.println(url);

        String result = restTemplate.getForEntity(url + "/directions-service/hello", String.class).getBody();


        return result;
    }

    @GetMapping("/directions2")
    public String fetchHello() {
        return service.helloFromDirections();
    }
    @GetMapping("/directions3")
    public List<DirectionDTO> fetchDirections() {
        return service.findAllDirections();
    }
}
