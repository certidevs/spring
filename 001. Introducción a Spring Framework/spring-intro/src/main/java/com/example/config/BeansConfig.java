package com.example.config;

import com.example.primary.CustomerAServiceImpl;
import com.example.primary.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:beans.xml")
public class BeansConfig {

    /*
    Esta opción se utiliza para configurar clases que provienen de frameworks externos
     */
    @Bean
    public CustomerService customerService(){
        return new CustomerAServiceImpl();
    }

}
