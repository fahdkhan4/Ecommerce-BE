package com.example.ecommerce.service;

import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;
    public List<OrderItem> addOrderItems(List<OrderItem> items,int orderId) {
        for(OrderItem orderItem:items){
            orderItem.setOrderId(orderId);
            orderItemRepository.addOrderItems(orderItem);
        }

       return orderItemRepository.getOrderItemsByOrderId(items.get(0).getOrderId());
    }
}
