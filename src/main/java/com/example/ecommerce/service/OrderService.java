package com.example.ecommerce.service;

import com.example.ecommerce.config.EmailService;
import com.example.ecommerce.model.Invoice;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.helper.*;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    EmailService emailService;


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

        /**
         * Generate Invoice
         */
        invoiceService.addInvoice(new Invoice(order.getId(), LocalDate.now()));


        String mainEmailDescription = "Dear " + userDTO.getFirstName() + ",\n\n" +
                "Thank you for your order! We’re excited to let you know that your order has been successfully confirmed.\n\n" +
                "Here are your order details:\n\n";

        HashMap<String, String> listDetails = new HashMap<>();
        listDetails.put("* Order Number", String.valueOf(order.getId()));
        listDetails.put("* Date", LocalDate.now().toString());
        listDetails.put("* Total Amount", order.getTotalAmount().toString());

        StringBuilder emailContent = new StringBuilder(mainEmailDescription);

        for (Map.Entry<String, String> entry : listDetails.entrySet()) {
            emailContent.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        String footerDetails =
                "If you have any questions, feel free to reach out to our customer support team.\n\n" +
                        "Thank you for choosing us!\n" +
                        "Best regards,\n" +
                        "Mahad, Rafey, Fahd";

        emailContent.append("\n").append(footerDetails);

        String finalEmailMessage = emailContent.toString();


        emailService.sendEmail(
                "FAHAD.G24679@iqra.edu.pk"
                ,"Ecommercestore819@gmail.com"
                ,"Your Order has been Confirmed "+LocalDate.now()
                ,finalEmailMessage
                );


        return new OrderResponse(order.getId(),userDTO,products,order.getTotalAmount(),order.getPaymentId());
    }




}
