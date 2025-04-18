package com.mycompany.app.my_app_simple.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id") // 對應資料庫的欄位名稱
    private Long customerId;
    
    @Column(name = "name")
    private String customerName;
    
    private String email;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY) // 或 FetchType.EAGER
    @JsonManagedReference // 主控方
    private List<Order> orders;

    
    // Getters and Setters

  
}