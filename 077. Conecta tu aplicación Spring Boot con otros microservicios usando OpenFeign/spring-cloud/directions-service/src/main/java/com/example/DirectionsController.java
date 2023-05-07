package com.example;

import com.example.dto.DirectionDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/directions-service")
public class DirectionsController {

    @GetMapping("/hello")
    public String hello(){
//        return "Hello from Directions Service";
        throw new IllegalStateException("Error");
    }

    @GetMapping("/all")
    public List<DirectionDTO> findAll() throws InterruptedException {
        Thread.sleep(6000L);
        return List.of(
                new DirectionDTO(1L, "Calle 1"),
                new DirectionDTO(2L, "Calle 2"),
                new DirectionDTO(3L, "Calle 3")
        );
    }

}
