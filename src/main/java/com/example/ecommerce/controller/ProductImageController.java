package com.example.ecommerce.controller;

import com.example.ecommerce.config.image.UploadProductImageService;
import com.example.ecommerce.model.ProductImage;
import com.example.ecommerce.model.ProductImageDTO;
import com.example.ecommerce.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/product-image")
public class ProductImageController {

    @Autowired
    UploadProductImageService uploadProductImageService;

    @Autowired
    ProductImageService productImageService;

    @PostMapping("/add")
    public ProductImage saveProductImage(
                                         @RequestParam("product_id") int productId,
                                         @RequestParam("image") MultipartFile image){

        ProductImage productImage = new ProductImage(productId,image);
        String filePath = uploadProductImageService.uploadImage(image);
        productImage.setImage_path(filePath);

        return productImageService.saveProductImage(productImage);
    }
    @GetMapping("/get/{productId}")
    public List<ProductImageDTO> getProductImageByProductId(@PathVariable int productId) throws IOException {
        return productImageService.getProductImageByProductId(productId);
    }
}
