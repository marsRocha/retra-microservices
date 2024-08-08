package com.retra.order_service.service;

import com.retra.order_service.dto.OrderSetDTO;
import com.retra.order_service.model.Order;
import com.retra.order_service.model.OrderStatus;
import com.retra.order_service.model.Shipping;
import com.retra.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    //private final EventProducerConfiguration eventProducerConfiguration;

    private static final String ORDER_NOT_FOUND = "ORDER %d not found";

    public void createOrder(OrderSetDTO orderSetDTO) {
        // TODO: make userId sent over authToken instead of sending buyerId

        Shipping s = new Shipping(orderSetDTO.address(), orderSetDTO.country());

        Order o = Order.builder()
                .buyerId(orderSetDTO.buyerId())
                .sellerId(orderSetDTO.sellerId())
                .productId(orderSetDTO.productId())
                .quantity(orderSetDTO.quantity())
                .shipping(s)
                .sellerId(orderSetDTO.sellerId())
                .build();
        orderRepository.save(o);

        //eventProducerConfiguration.sendMessage(o);
    }

    public void deleteOrder(Long orderId) {
        if(!orderRepository.existsById(orderId)) {
            throw new IllegalStateException(String.format(ORDER_NOT_FOUND, orderId));
        }
        orderRepository.deleteById(orderId);
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException(String.format(ORDER_NOT_FOUND, orderId)));
    }
}
