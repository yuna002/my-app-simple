package com.mycompany.app.my_app_simple.controller;


import com.mycompany.app.my_app_simple.model.Product;
import com.mycompany.app.my_app_simple.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    // 取得所有產品清單
    @GetMapping(value = "/getAllProduct", produces = "application/json")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    
    // 新增產品
    @PostMapping(value = "/addProduct", consumes = "application/json", produces = "application/json")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
    
}