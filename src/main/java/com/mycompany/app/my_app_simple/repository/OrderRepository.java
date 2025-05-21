package com.mycompany.app.my_app_simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.app.my_app_simple.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

   
}