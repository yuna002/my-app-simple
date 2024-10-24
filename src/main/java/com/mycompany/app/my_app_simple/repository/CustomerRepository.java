package com.mycompany.app.my_app_simple.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.app.my_app_simple.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}