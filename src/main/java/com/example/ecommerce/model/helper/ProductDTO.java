package com.example.ecommerce.model.helper;

import com.example.ecommerce.model.ProductImage;
import com.example.ecommerce.model.ProductImageDTO;

import java.time.LocalDate;
import java.util.List;

public class ProductDTO {

    private int id;
    private String name;
    private Double price;

    private String description;
    private Double weight;

    private String dimension;

    private Integer quantity;

    private LocalDate uploadedDate;

    private String status;

    private int user_id;

    private int category_id;

    List<ProductImageDTO> productImages;

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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(LocalDate uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public ProductDTO(int id, String name, Double price, String description, Double weight, String dimension, Integer quantity, LocalDate uploadedDate, String status, int user_id, int category_id, List<ProductImageDTO> productImages) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.weight = weight;
        this.dimension = dimension;
        this.quantity = quantity;
        this.uploadedDate = uploadedDate;
        this.status = status;
        this.user_id = user_id;
        this.category_id = category_id;
        this.productImages = productImages;
    }
}
