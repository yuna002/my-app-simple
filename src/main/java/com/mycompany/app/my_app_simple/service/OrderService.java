package com.mycompany.app.my_app_simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.app.my_app_simple.model.Order;
import com.mycompany.app.my_app_simple.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // 查詢該客戶的所有訂單
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomer_CustomerId(customerId);
    }

    // 新增訂單
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    // 刪除訂單
    public void removeOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            orderRepository.deleteById(orderId);
        } else {
            throw new RuntimeException("Order with id " + orderId + " not found.");
        }
    }

    // 查詢所有客戶及訂單
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}