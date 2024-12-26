package com.example.ecommerce.repository;


import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.helper.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderItemRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addOrderItems(OrderItem orderItem) {
        jdbcTemplate.update(
                "INSERT INTO order_item (order_id,product_id,quantity,price) " +
                        "VALUES (?,?,?,?)",
                orderItem.getOrderId(),
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );

    }


    public List<OrderItem> getOrderItemsByOrderId(int id) {
        String query = "Select * from order_item where order_id=?";
        Object[] arg = {id};
        return jdbcTemplate.query(query,arg,rs->{
            List<OrderItem> order = new ArrayList<>();
            while(rs.next()){
                order.add(new OrderItem(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDouble(4)
                ));
            }
            return order;
        });
    }

}
