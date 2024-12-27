package com.example.ecommerce.repository;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Payment addPayment(Payment payment){
        jdbcTemplate.update(
                "INSERT INTO payment (amount,payment_method,status) " +
                        "VALUES (?,?,?)",
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus()
        );

        return getPaymentById(getLatestPaymentId().get());
    }

    public List<Payment> getAllPayment(){
        return jdbcTemplate.query("SELECT * FROM payment", rs -> {
            List<Payment> payments = new ArrayList<>();

            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
            return payments;
        });
    }

    public Payment getPaymentById(int id){
        String query = "Select * from payment where id=?";
        Object[] arg = {id};
        return jdbcTemplate.query(query,arg,rs->{
            Payment payment = null;
            if(rs.next()) {
                payment = new Payment(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
            return payment;
        });
    }

    public Optional<Integer> getLatestPaymentId(){
        String query = "SELECT * FROM payment ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }
            return Optional.empty();
        });
    }
}
