package com.retra.product_service.service;

import com.retra.product_service.repository.ProductRepository;
import com.retra.product_service.clients.ReviewClient;
import com.retra.product_service.dto.ProductSetDTO;
import com.retra.product_service.dto.ProductWithDetails;
import com.retra.product_service.model.Product;
import com.retra.review_service.model.Review;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewClient reviewClient;

    private static final String PRODUCT_NOT_FOUND = "Product %d not found";

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        return productRepository.findAll();
    }

    public void createProduct(ProductSetDTO productSetDTO) {
        Product p = Product.builder()
                .name(productSetDTO.name())
                .description(productSetDTO.description())
                .price(productSetDTO.price())
                .build();
        productRepository.save(p);
    }

    @Transactional
    public void editProduct(ProductSetDTO productSetDTO, Long productId) {
        Product p = getProductById(productId);

        if(productSetDTO.name() != null && !productSetDTO.name().isEmpty()) {
            p.setName(productSetDTO.name());
        }
        if(productSetDTO.description() != null && !productSetDTO.description().isEmpty()) {
            p.setDescription(productSetDTO.description());
        }
        if(productSetDTO.price() != null) {
            p.setPrice(productSetDTO.price());
        }
    }

    public void deleteProduct(Long productId) {
        if(!productRepository.existsById(productId)) {
            throw new IllegalStateException(String.format(PRODUCT_NOT_FOUND, productId));
        }
        productRepository.deleteById(productId);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(String.format(PRODUCT_NOT_FOUND, productId)));
    }

    public ProductWithDetails getProductWithDetails(Long productId) {

        Product p = getProductById(productId);

        // get product reviews from REVIEW-SERVICE
        List<Review> reviews = reviewClient.getReviews(productId);

        return new ProductWithDetails(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                reviews);
    }
}
