package com.mycompany.app.my_app_simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.mycompany.app.my_app_simple.service.OrderService;
import com.mycompany.app.my_app_simple.model.Order;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 取得該客戶的所有訂單
    @GetMapping(value = "/getOrder", produces = "application/json")
    public ResponseEntity<List<Order>> getOrder(@RequestParam Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    // 新增該客戶訂單
    @PostMapping("/addOrder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order createdOrder = orderService.addOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    // 刪除該客戶訂單
    @PostMapping("/removeOrder")
    public ResponseEntity<Void> removeOrder(@RequestParam Long orderId) {
        orderService.removeOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    // 取得所有客戶及訂單
    @PostMapping("/getCustomerAndOrder")
    public ResponseEntity<List<Order>> getCustomerAndOrder() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}