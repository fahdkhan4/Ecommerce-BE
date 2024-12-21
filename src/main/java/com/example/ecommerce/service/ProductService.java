package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.helper.ProductDTO;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageService productImageService;

    public Product addProduct(Product product){
       return productRepository.addProduct(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllProduct();
    }

    public Product getProductById(int id){
        return productRepository.getProductById(id);
    }

    public List<ProductDTO> getProductsByUserId(int userId) throws IOException {
        List<ProductDTO> products = productToDTO(productRepository.getProductByUserId(userId));

        for(ProductDTO product:products){
            product.setProductImages(productImageService.getProductImageByProductId(product.getId()));
        }

        return products;
    }

    public List<ProductDTO> productToDTO(List<Product> products){
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product:products){
            productDTOS.add(new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getWeight(),
                    product.getDimension(),
                    product.getQuantity(),
                    product.getUploadedDate(),
                    product.getStatus(),
                    product.getUser_id(),
                    product.getCategory_id(),
                    null
            ));
        }

        return productDTOS;
    }
}
