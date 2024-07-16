package com.retra.review_service.messaging;

import com.retra.review_service.dto.messages.ReviewMessage;
import com.retra.review_service.model.Review;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventProducerConfiguration {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Review review) {
        ReviewMessage reviewMessage = new ReviewMessage(
                review.getId(),
                review.getDescription(),
                review.getRating(),
                review.getProductId(),
                review.getUserId());
        rabbitTemplate.convertAndSend("productReviewQueue", reviewMessage);
    }
}
