package com.example.ecommerce.service;

import com.example.ecommerce.model.Invoice;
import com.example.ecommerce.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.getAllInvoices();
    }

    public Invoice getInvoiceById(int id) {
        return invoiceRepository.getInvoiceById(id);
    }

    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.addInvoice(invoice);
    }

}
