package com.mycompany.app.my_app_simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.app.my_app_simple.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // 根據客戶ID查詢訂單
    List<Order> findByCustomer_CustomerId(Long customerId);

    // 刪除訂單（已由 JpaRepository 提供 deleteById）
}