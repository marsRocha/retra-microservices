package com.retra.review_service;

public class Review {
    private Long id;
    private String description;
    private int rating;

    private Long productId;
    private Long userId;

    public Review(Long id, String description, int rating, Long productId, Long userId) {
        this.id = id;
        this.description = description;
        this.rating = rating;
        this.productId = productId;
        this.userId = userId;
    }
}
