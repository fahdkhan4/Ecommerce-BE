package com.example.ecommerce.service;

import com.example.ecommerce.model.ProductImage;
import com.example.ecommerce.model.ProductImageDTO;
import com.example.ecommerce.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageService {

    private final String imageFolderPath = Paths.get("src/main/resources/static/product/images").toAbsolutePath().toString();

    @Autowired
    ProductImageRepository productImageRepository;

    public ProductImage saveProductImage(ProductImage productImage){
        return productImageRepository.addProductImage(productImage);
    }

    public List<ProductImage> getProductImages(){
        return productImageRepository.getAllProductImage();
    }

    public ProductImage getProductImageById(int id){
        return productImageRepository.getProductImageById(id);
    }

    public List<ProductImageDTO> getProductImageByProductId(int productId) throws IOException {
        List<ProductImage>  productImages = productImageRepository.getProductImageByProductId(productId);
        List<ProductImageDTO> productImageDTOS = new ArrayList<>();

        for (ProductImage productImage:productImages){
            productImageDTOS.add(
                    new ProductImageDTO(
                            productImage.getId(),
                            productImage.getProduct_id(),
                            getImageByName(productImage.getImage_path()),
                            productImage.getImage_path()
                    ));
        }

        return productImageDTOS;
    }

    public byte[] getImageByName(String imagePath) throws IOException {

        Path image = Path.of(imagePath);

        if (Files.exists(image)) {
            byte[] imageBytes = Files.readAllBytes(image);
            return imageBytes;
        } else {
            return null;
        }
    }

    private String determineContentType(String filename) {
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG_VALUE;
        } else if (filename.endsWith(".png")) {
            return MediaType.IMAGE_PNG_VALUE;
        }
        return null;
    }


}
