package com.example.ecommerce.model;

public class Order {
    private int id;
    private Double totalAmount;
    private int paymentId;
    private int userId;

    public Order(Double totalAmount, int paymentId, int userId) {
        this.totalAmount = totalAmount;
        this.paymentId = paymentId;
        this.userId = userId;
    }

    public Order(int id, Double totalAmount, int paymentId, int userId) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.paymentId = paymentId;
        this.userId = userId;
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
}
