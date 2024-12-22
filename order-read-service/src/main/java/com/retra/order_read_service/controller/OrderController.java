package com.retra.order_service.controller;

import com.retra.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Order> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

}
