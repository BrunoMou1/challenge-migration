package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.config.ProductClient;
import com.example.dummyjson.dto.ProductListResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductClient productClient;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        Map<String, Object> mockedResponse = new HashMap<>();
        List<Map<String, Object>> productsList = Arrays.asList(
                Map.of("id", 1L, "title", "Product 1", "description", "Description 1", "price", 100.0),
                Map.of("id", 2L, "title", "Product 2", "description", "Description 2", "price", 150.0)
        );
        mockedResponse.put("products", productsList);

        when(productClient.getAllProducts()).thenReturn(mockedResponse);

        List<Map<String, Object>> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).get("title"));
        assertEquals("Product 2", result.get(1).get("title"));
    }



    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");
        product.setDescription("Description 1");
        product.setPrice(100.0);

        when(productClient.getProductById(1L)).thenReturn(product);

        Product result = productService.getProductById(1L);

        assertEquals("Product 1", result.getTitle());
        assertEquals("Description 1", result.getDescription());
        assertEquals(100.0, result.getPrice());
    }
}
