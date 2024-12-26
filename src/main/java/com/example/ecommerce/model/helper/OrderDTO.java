package com.example.ecommerce.model.helper;

import com.example.ecommerce.model.OrderItem;

import java.util.List;

public class OrderDTO {
    private int id;
    private Double totalAmount;
    private int paymentId;
    private int userId;
    List<OrderItem> items;

    public OrderDTO() {
    }

    public OrderDTO(Double totalAmount, int paymentId, int userId, List<OrderItem> items) {
        this.totalAmount = totalAmount;
        this.paymentId = paymentId;
        this.userId = userId;
        this.items = items;
    }

    public OrderDTO(int id, Double totalAmount, int paymentId, int userId, List<OrderItem> items) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.paymentId = paymentId;
        this.userId = userId;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
