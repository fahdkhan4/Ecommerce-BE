package com.example.ecommerce.controller;

import com.example.ecommerce.model.helper.OrderDTO;
import com.example.ecommerce.model.helper.OrderResponse;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public OrderResponse generateOrder(@RequestBody OrderDTO orderDTO) throws IOException {
        if(Objects.isNull(orderDTO)){
            throw new IllegalArgumentException("Invalid Arg");
        }

        return orderService.generateOrder(orderDTO);
    }


}
