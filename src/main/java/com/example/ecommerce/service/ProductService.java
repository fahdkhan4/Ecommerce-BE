package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product addProduct(Product product){
       return productRepository.addProduct(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllProduct();
    }

    public Product getProductById(int id){
        return productRepository.getProductById(id);
    }
}
