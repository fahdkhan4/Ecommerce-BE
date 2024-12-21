package com.example.ecommerce.model;

import org.springframework.core.io.Resource;

import java.io.InputStream;

public class ProductImageDTO {
    private int id;
    private int product_id;

    private byte[] image;

    private String imagePath;


    public ProductImageDTO(int id, int product_id, byte[] image, String imagePath) {
        this.id = id;
        this.product_id = product_id;
        this.image = image;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
