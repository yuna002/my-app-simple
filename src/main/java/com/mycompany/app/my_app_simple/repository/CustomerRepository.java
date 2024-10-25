package com.mycompany.app.my_app_simple.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mycompany.app.my_app_simple.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders")
    List<Customer> findAllWithOrders();
	
}