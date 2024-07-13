package com.retra.product_service.dto;

import com.retra.review_service.model.Review;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductWithDetails {
    private Long id;
    private String name;
    private String description;
    private Double price;

    private List<Review> reviews;
}
