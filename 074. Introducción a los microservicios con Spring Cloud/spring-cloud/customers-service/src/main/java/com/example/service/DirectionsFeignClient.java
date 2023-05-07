package com.example.service;

import com.example.dto.DirectionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "directions", url = "http://localhost:8080")
public interface DirectionsFeignClient {

    @GetMapping("/directions-service/hello")
    String fetchHello();

    @GetMapping("/directions-service/all")
    List<DirectionDTO> fetchDirections();

}
