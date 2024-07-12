package com.retra.review_service.controller;

import com.retra.review_service.service.ReviewService;
import com.retra.review_service.dto.ReviewSetDTO;
import com.retra.review_service.model.Review;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private static final String REVIEW_CREATE_LOG = "Review was successfully created";
    private static final String REVIEW_EDIT_LOG = "Review {} edited created";
    private static final String REVIEW_DELETE_LOG = "Review {} was deleted";

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam(value = "productId", required = false) Long productId) {
        if(productId != null) {
            return new ResponseEntity<>(reviewService.getReviewsByProduct(productId), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(reviewService.getReviews(), HttpStatus.OK);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody ReviewSetDTO reviewSetDTO, @RequestParam Long productId, @RequestParam Long userId) {
        reviewService.createReview(reviewSetDTO, productId, userId);
        log.info(REVIEW_CREATE_LOG);
    }

    @PutMapping(path = "/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void editReview(@RequestBody ReviewSetDTO reviewSetDTO, @PathVariable Long reviewId){
        reviewService.editReview(reviewSetDTO, reviewId);
        log.info(REVIEW_EDIT_LOG, reviewId);
    }

    @DeleteMapping(path = "/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
        log.info(REVIEW_DELETE_LOG, reviewId);
    }

    @GetMapping(path = "/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(reviewId), HttpStatus.OK);
    }
}
