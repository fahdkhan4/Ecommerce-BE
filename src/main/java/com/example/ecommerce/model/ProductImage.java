package com.example.ecommerce.model;

public class ProductImage {

    private int id;
    private String image_path;
    private int product_id;

    public ProductImage(int id, String image_path, int product_id) {
        this.id = id;
        this.image_path = image_path;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
