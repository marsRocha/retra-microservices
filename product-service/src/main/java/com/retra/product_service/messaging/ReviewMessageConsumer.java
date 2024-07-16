package com.retra.product_service.messaging;

import com.retra.product_service.dto.messages.ReviewMessage;
import com.retra.product_service.model.Product;
import com.retra.product_service.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewMessageConsumer {

    private final ProductService productService;

    @RabbitListener(queues = "productReviewQueue")
    @Transactional
    public void createReview(ReviewMessage reviewMessage) {
        Product p = productService.getProductById(reviewMessage.getProductId());
        p.setNumberReviews(p.getNumberReviews() + 1);
    }
}
