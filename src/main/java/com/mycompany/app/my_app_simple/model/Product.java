package com.mycompany.app.my_app_simple.model;

import jakarta.persistence.*;
import java.math.BigDecimal;


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
    private BigDecimal price;
    
    @Column(name="stock",nullable = false)
    private Integer stock;
    
    
    // Getters and Setters

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stock;
	}

	public void setStockQuantity(Integer stock) {
		this.stock = stock;
	}

  
   
}