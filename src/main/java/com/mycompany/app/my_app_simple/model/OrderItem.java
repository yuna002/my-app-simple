package com.mycompany.app.my_app_simple.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference // 被控方
    private Order order;
    
    @Column(name="product_id")
    private Long productId;
    
    private int quantity;
    private Double price;

    // Getters and Setters

   
    
}