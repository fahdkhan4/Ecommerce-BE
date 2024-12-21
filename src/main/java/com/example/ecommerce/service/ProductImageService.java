package com.example.ecommerce.service;

import com.example.ecommerce.model.ProductImage;
import com.example.ecommerce.model.ProductImageDTO;
import com.example.ecommerce.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    public List<ProductImageDTO> getProductImageByProductId(int productId) throws FileNotFoundException {
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

    public InputStream getImageByName(String imagePath) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(imagePath);
        return inputStream;
    }


}
