package com.retra.review_service.dto.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMessage {
    private Long id;
    private String description;
    private int rating;
    private Long productId;
    private Long userId;
}
