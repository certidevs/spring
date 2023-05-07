package com.example.service;

import com.example.dto.DirectionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private DirectionsFeignClient client;

    public String helloFromDirections() {
        return client.fetchHello();
    }

    public List<DirectionDTO> findAllDirections() {
        return client.fetchDirections();
    }
}
