package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");
        product1.setDescription("Description 1");
        product1.setPrice(100.0);

        product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(150.0);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Map<String, Object>> products = Arrays.asList(
                Map.of("id", 1L, "title", "Product 1", "description", "Description 1", "price", 100.0),
                Map.of("id", 2L, "title", "Product 2", "description", "Description 2", "price", 150.0)
        );
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Product 1"))
                .andExpect(jsonPath("$[1].title").value("Product 2"));
    }


    @Test
    public void testGetProductById() throws Exception {
        when(productService.getProductById(1L)).thenReturn(product1);

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Product 1"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    public void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", equalTo("UP")));
    }
}
