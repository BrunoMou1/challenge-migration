package com.example.dummyjson.dto;

import java.util.List;

public class ProductListResponse {
    private List<Product> data;

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}