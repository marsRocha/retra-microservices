package com.retra.product_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    RestTemplate restTemplate;

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Super Mario 64", "Game Cube Mario game", 49.99));
        return products;
    }
}
