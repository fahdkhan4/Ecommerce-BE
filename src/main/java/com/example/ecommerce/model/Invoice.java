package com.example.ecommerce.model;

import java.time.LocalDate;

public class Invoice {

    private int id;

    private int orderId;

    private LocalDate invoiceDate;


    public Invoice() {
    }

    public Invoice(int orderId, LocalDate invoiceDate) {
        this.orderId = orderId;
        this.invoiceDate = invoiceDate;
    }

    public Invoice(int id, int orderId, LocalDate invoiceDate) {
        this.id = id;
        this.orderId = orderId;
        this.invoiceDate = invoiceDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
