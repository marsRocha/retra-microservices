package com.retra.review_service.service;

import com.retra.review_service.messaging.EventProducerConfiguration;
import com.retra.review_service.repository.ReviewRepository;
import com.retra.review_service.dto.ReviewSetDTO;
import com.retra.review_service.model.Review;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final EventProducerConfiguration eventProducerConfiguration;

    private static final String REVIEW_NOT_FOUND = "Review %d not found";

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findAllByProductId(productId);
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalStateException(String.format(REVIEW_NOT_FOUND, reviewId)));
    }

    public void createReview(ReviewSetDTO reviewSetDTO, Long productId, Long userId) {
        // TODO: make userId sent over authToken

        Review r = Review.builder()
                .description(reviewSetDTO.description())
                .rating(reviewSetDTO.rating())
                .productId(productId)
                .userId(userId)
                .build();
        reviewRepository.save(r);

        eventProducerConfiguration.sendMessage(r);
    }

    @Transactional
    public void editReview(ReviewSetDTO reviewSetDTO, Long reviewId) {
        // TODO: use authToken to know if user that is modifying is the one who created the review

        Review r = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalStateException(String.format(REVIEW_NOT_FOUND, reviewId)));

        // validate new data
        if(reviewSetDTO.description() != null && !reviewSetDTO.description().isEmpty()) {
            r.setDescription(reviewSetDTO.description());
        }
        if(reviewSetDTO.rating() >= 1 && reviewSetDTO.rating() <= 5) {
            r.setRating(reviewSetDTO.rating());
        }
    }

    public void deleteReview(Long reviewId) {
        if(!reviewRepository.existsById(reviewId)) {
            throw new IllegalStateException(String.format(REVIEW_NOT_FOUND, reviewId));
        }
        reviewRepository.deleteById(reviewId);
    }
}
