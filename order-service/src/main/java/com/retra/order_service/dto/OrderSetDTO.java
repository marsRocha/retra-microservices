package com.retra.order_service.dto;

public record OrderSetDTO(
    Long buyerId,
    Long sellerId,
    Long productId,
    int quantity,
    String address,
    String country
) {
}
