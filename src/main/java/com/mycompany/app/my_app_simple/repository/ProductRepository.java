package com.mycompany.app.my_app_simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.app.my_app_simple.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}