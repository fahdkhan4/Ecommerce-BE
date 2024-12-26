package com.example.ecommerce.model.helper;

import com.example.ecommerce.model.ProductImageDTO;

import java.util.List;

public class OrderProductResponse {

    private int id;

    private String name;
    private Double price;

    private String description;

    private Integer quantity;

    private Double totalPrice;

    private int user_id;

    private int category_id;

    List<ProductImageDTO> productImages;

    public OrderProductResponse() {
    }

    public OrderProductResponse(int id, String name, Double price, String description, Integer quantity, Double totalPrice, int user_id, int category_id, List<ProductImageDTO> productImages) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.user_id = user_id;
        this.category_id = category_id;
        this.productImages = productImages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public List<ProductImageDTO> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImageDTO> productImages) {
        this.productImages = productImages;
    }
}
