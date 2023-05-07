package com.example.controller;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private StorageService storageService;

    // http://localhost:8080/products
    @GetMapping("/products")
    public String findAll(Model model){
        model.addAttribute("products", productRepo.findAll());
        return "product_list";
    }
    @PostMapping("/products")
    public String submit(
            @ModelAttribute Product product,
            @RequestParam("file") MultipartFile file
    ){
        if(!file.isEmpty()){
            String image = storageService.store(file);
            String imageUrl = MvcUriComponentsBuilder.
                    fromMethodName(
                            FileController.class, "serveFile", image
                    ).build().toUriString();
            product.setImage(imageUrl);
        }
        productRepo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/new")
    public String create(Model model){
        model.addAttribute("product", new Product());
        return "product_form";
    }


}
