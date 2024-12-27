package com.example.ecommerce.service;


import com.example.ecommerce.model.Payment;
import com.example.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public Payment addPayment(Payment payment){

        if(payment.getPaymentMethod().equalsIgnoreCase("Card")){
            payment.setStatus("Success");
        }
        else{
            payment.setStatus("Failed");
        }

        return paymentRepository.addPayment(payment);
    }

    public List<Payment> getAllPayment(){
        return paymentRepository.getAllPayment();
    }

    public Payment getPaymentById(int id) {
        return paymentRepository.getPaymentById(id);
    }
}
