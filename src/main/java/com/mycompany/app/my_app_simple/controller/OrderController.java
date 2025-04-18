package com.mycompany.app.my_app_simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.app.my_app_simple.model.Customer;
import com.mycompany.app.my_app_simple.model.Order;
import com.mycompany.app.my_app_simple.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

   
    
    @PostMapping("/getOrder")
    public ResponseEntity<Order> getOrder(@RequestParam Long orderId) {
        return orderService.getOrder(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addOrder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order savedOrder = orderService.addOrder(order);
        return ResponseEntity.ok(savedOrder);
    }
    
    @PostMapping("/removeOrder")
    public ResponseEntity<Void> removeOrder(@RequestParam Long orderId) {
        orderService.removeOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/getCustomerAndOrder")
    public ResponseEntity<List<Customer>> getCustomerAndOrder() {
        List<Customer> customers = orderService.getCustomersWithOrders();
        return ResponseEntity.ok(customers);
    }
    
    

    
}