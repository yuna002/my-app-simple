package com.mycompany.app.my_app_simple.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.app.my_app_simple.model.Customer;
import com.mycompany.app.my_app_simple.model.Order;
import com.mycompany.app.my_app_simple.model.OrderItem;
import com.mycompany.app.my_app_simple.repository.CustomerRepository;
import com.mycompany.app.my_app_simple.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;


    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }
    
    public Order addOrder(Order order) {
        // 確保設置每個 OrderItem 的 order 屬性
        for (OrderItem item : order.getOrderItems()) {
            item.setOrder(order); // 設置 Order 參考
        }
        return orderRepository.save(order); // 保存訂單及其項目
    }

    public void removeOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Customer> getCustomersWithOrders() {
    	return customerRepository.findAllWithOrders();
    }
}