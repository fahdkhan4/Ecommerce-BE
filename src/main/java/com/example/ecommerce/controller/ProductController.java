package com.example.ecommerce.controller;


import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.helper.ProductDTO;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public List<ProductDTO> getAllProducts() throws IOException {
        return productService.getAllProducts();
    }

    @GetMapping("/get/{id}")
    public ProductDTO getProductById(@PathVariable int id) throws IOException {
        if(Objects.isNull(id)){
            System.out.println("Product id cannot be null");
        }
        return productService.getProductById(id);
    }


    @GetMapping("/get/user/{user_id}")
    public List<ProductDTO> getProductsByUserId(@PathVariable int user_id) throws IOException {
        if(Objects.isNull(user_id)){
            System.out.println("Product id cannot be null");
        }
        return productService.getProductsByUserId(user_id);
    }

    @GetMapping("/get/category/{category}")
    public List<ProductDTO> filterProductsByCategory(@PathVariable String category) throws IOException {
        if(category.isEmpty() || category.isBlank()){
            throw new IllegalArgumentException("Cannot filter products Category cannot be empty");
        }
        return productService.filterProductsByCategory(category);
    }

    @GetMapping("/get/search/{search}")
    public List<ProductDTO> searchProducts(@PathVariable String search) throws IOException {
        if(search.isEmpty() || search.isBlank()){
            throw new IllegalArgumentException("Cannot filter products Category cannot be empty");
        }
        return productService.filterProductsByName(search);
    }



}
