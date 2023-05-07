package com.example;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*

GET /{supplier}
POST /{consumer}
POST /{function}

 */

@Configuration
public class Functions {

    @Bean
    public Function<String, String> reverse(){
        return value -> new StringBuilder(value).reverse().toString();
    }

//    @Bean
//    public Consumer<String> consumeText(){
//        return text -> System.out.println(text);
//    }
//
//    @Bean
//    public Supplier<String> produceText(){
//        return () -> "Hello World";
//    }
//
//    @Bean
//    public Supplier<HelloDTO> getHello(){
//        return () -> new HelloDTO(1L, "Hello World");
//    }
//
//    @Bean
//    public Supplier<List<HelloDTO>> getHellos(){
//        return () -> List.of(
//                new HelloDTO(1L, "Hello World"),
//                new HelloDTO(2L, "Hello World 2")
//        );
//    }

}
