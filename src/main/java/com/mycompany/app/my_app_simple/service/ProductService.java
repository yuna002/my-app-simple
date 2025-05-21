package com.mycompany.app.my_app_simple.service;

import com.mycompany.app.my_app_simple.model.Product;
import com.mycompany.app.my_app_simple.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 取得所有產品
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 新增產品
    public Product addProduct(Product product) {
        return productRepository.save(product);  // 使用 JPA 的 save() 方法保存產品
    }
}