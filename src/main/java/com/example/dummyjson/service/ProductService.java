package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.config.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductClient productClient;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getAllProducts() {
        Map<String, Object> response = productClient.getAllProducts();
        return (List<Map<String, Object>>) response.get("products");
    }

    public Product getProductById(Long id) {
        return productClient.getProductById(id);
    }
}
