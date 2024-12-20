package com.example.ecommerce.controller;


import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        if(Objects.isNull(product)){
            System.out.println("Cannot add product is empty");
        }

        return productService.addProduct(product);
    }

    @GetMapping("/get")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable int id){
        if(Objects.isNull(id)){
            System.out.println("Product id cannot be null");
        }
        return productService.getProductById(id);
    }




}
