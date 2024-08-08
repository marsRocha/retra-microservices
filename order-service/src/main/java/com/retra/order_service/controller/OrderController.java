package com.retra.order_service.controller;

import com.retra.order_service.dto.OrderSetDTO;
import com.retra.order_service.model.Order;
import com.retra.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;

    private static final String ORDER_CREATE_LOG = "Order was successfully created";
    private static final String ORDER_EDIT_LOG = "Order {} was edited";
    private static final String ORDER_DELETE_LOG = "Order {} was deleted";

    @PostMapping
    public void createOrder(@RequestBody OrderSetDTO orderSetDTO) {
        orderService.createOrder(orderSetDTO);
        log.info(ORDER_CREATE_LOG);
    }

    @DeleteMapping(path = "/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        log.info(ORDER_DELETE_LOG, orderId);
    }

}
