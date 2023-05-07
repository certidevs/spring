package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    ProductService service;

    /*
    GET http://localhost:8080/products/new
     */
    @GetMapping("/products/new")
    public String getForm(Model model){
        model.addAttribute("product", new Product());
        return "product-form";
    }

    /*
    POST http://localhost:8080/products
     */
    @PostMapping("/products")
    public String createProduct(Model model, @Valid Product product, BindingResult validation){
        // validaciones personalizadas....
        // .....

        // validaciones de Spring
        if (validation.hasErrors())
            return "product-form";

        System.out.println(product);

        // Guardar en base de datos....
        model.addAttribute("product", product);
//        service.findById(1L);
        service.save(product);
        return "product-info";
    }

}
