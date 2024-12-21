package com.example.ecommerce.model;

import java.io.InputStream;

public class ProductImageDTO {
    private int id;
    private int product_id;

    private InputStream image;

    private String imagePath;


    public ProductImageDTO(int id, int product_id, InputStream image, String imagePath) {
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

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
