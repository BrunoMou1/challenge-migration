package com.example.dummyjson.config;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "productClient", url = "https://dummyjson.com/products")
public interface ProductClient {

    @GetMapping
    Map<String, Object> getAllProducts();

    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id);
}