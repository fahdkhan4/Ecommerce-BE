package com.example.ecommerce.model.helper;

import java.util.List;

public class OrderResponse {
    private int orderId;

    private UserDTO userDTO;

    List<OrderProductResponse> products;

    private Double totalAmount;

    private int paymentId;

    public OrderResponse(int orderId, UserDTO userDTO, List<OrderProductResponse> products, Double totalAmount, int paymentId) {
        this.orderId = orderId;
        this.userDTO = userDTO;
        this.products = products;
        this.totalAmount = totalAmount;
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<OrderProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductResponse> products) {
        this.products = products;
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
}
