package com.retra.product_service.clients;

import com.retra.product_service.model.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// TODO: CREATE A MODULE THAT HOLDS ALL FEIGN CLIENTS
@FeignClient(name="REVIEW-SERVICE", url = "${review-service.url}")
public interface ReviewClient {
    @GetMapping(path = "/reviews")
    List<Review> getReviews(@RequestParam("productId") Long productId);
}
