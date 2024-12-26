package com.example.ecommerce.repository;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.helper.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class OrderRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public Order generateOrder(OrderDTO orderDTO) {
        jdbcTemplate.update(
                "INSERT INTO `order` (total_amount,payment_id,user_id) VALUES (?,?,?)",
                orderDTO.getTotalAmount(),
                null,
                orderDTO.getUserId()
        );

        return getOrderById(getLatestOrderId().get());
    }

    private Order getOrderById(int id) {
        String query = "Select * from `order` where id=?";
        Object[] arg = {id};
        return jdbcTemplate.query(query,arg,rs->{
            Order order = null;
            if(rs.next()) {
                order = new Order(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
            }
            return order;
        });
    }

    public Optional<Integer> getLatestOrderId(){
        String query = "SELECT * FROM `order` ORDER BY id DESC LIMIT 1";

        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }
            return Optional.empty();
        });
    }


}
