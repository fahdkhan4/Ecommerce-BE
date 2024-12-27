package com.example.ecommerce.repository;


import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InvoiceRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Invoice addInvoice(Invoice invoice){
        jdbcTemplate.update(
                "INSERT INTO invoice (order_id,invoice_date) " +
                        "VALUES (?,?)",
                invoice.getOrderId(),
                invoice.getInvoiceDate()
        );

        return getInvoiceById(getLatestInvoiceId().get());
    }

    public List<Invoice> getAllInvoices(){
        return jdbcTemplate.query("SELECT * FROM invoice", rs -> {
            List<Invoice> invoices = new ArrayList<>();

            while (rs.next()) {
                invoices.add(new Invoice(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3).toLocalDate()
                ));
            }
            return invoices;
        });
    }

    public Invoice getInvoiceById(int id){
        String query = "Select * from invoice where id=?";
        Object[] arg = {id};
        return jdbcTemplate.query(query,arg,rs->{
            Invoice invoice = null;
            if(rs.next()) {
                invoice = new Invoice(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3).toLocalDate()
                );
            }
            return invoice;
        });
    }

    public Optional<Integer> getLatestInvoiceId(){
        String query = "SELECT * FROM invoice ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }
            return Optional.empty();
        });
    }
}
