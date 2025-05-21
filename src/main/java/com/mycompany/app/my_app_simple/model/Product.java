package com.mycompany.app.my_app_simple.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long productId;

    @Column(name="name",nullable = false, length = 100)
    private String productName;

    @Column(name="price",nullable = false)
    private Integer price;
    
    @Column(name="stock",nullable = false)
    private Integer stock;
    
    
    // Getters and Setters

   
}