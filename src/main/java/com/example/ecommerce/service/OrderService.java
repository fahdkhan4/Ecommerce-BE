package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.helper.*;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    UserService userService;


    public OrderResponse generateOrder(OrderDTO orderDTO) throws IOException {
        Order order = orderRepository.generateOrder(orderDTO);

        if(Objects.isNull(order)){
            throw new IllegalArgumentException("Issue while generating order");
        }

        List<OrderItem> orderItemList = orderItemService.addOrderItems(orderDTO.getItems(),order.getId());

        UserDTO userDTO = userService.getUserById(order.getUserId());

        /**
         * decrement the quantity of the products
         */

        productService.decrementProductQuantity(orderItemList);


        /**
         * generate product detail response
         */

        List<OrderProductResponse> products = new ArrayList<>();

        for(OrderItem orderItem:orderItemList){
            ProductDTO productDTO = productService.getProductById(orderItem.getProductId());
            products.add(
                    new OrderProductResponse(
                            productDTO.getId(),
                            productDTO.getName(),
                            productDTO.getPrice(),
                            productDTO.getDescription(),
                            orderItem.getQuantity(),
                            orderItem.getPrice(),
                            productDTO.getUser_id(),
                            productDTO.getCategory_id(),
                            productDTO.getProductImages()
                    ));
        }

        return new OrderResponse(order.getId(),userDTO,products,order.getTotalAmount(),order.getPaymentId());
    }
}
