package com.ecommerce.service;

import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Get all active products with pagination
     */
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        log.info("Fetching all active products with pagination: {}", pageable);
        return productRepository.findByActiveTrue(pageable)
            .map(this::toResponse);
    }

    /**
     * Get product by ID
     */
    public ProductResponse getProductById(Long productId) {
        log.info("Fetching product with ID: {}", productId);
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> {
                log.warn("Product not found with ID: {}", productId);
                return new RuntimeException("Product not found with ID: " + productId);
            });
        return toResponse(product);
    }

    /**
     * Get product by SKU
     */
    public ProductResponse getProductBySku(String sku) {
        log.info("Fetching product by SKU: {}", sku);
        Product product = productRepository.findBySku(sku)
            .orElseThrow(() -> {
                log.warn("Product not found with SKU: {}", sku);
                return new RuntimeException("Product not found with SKU: " + sku);
            });
        return toResponse(product);
    }

    /**
     * Get products by category
     */
    public Page<ProductResponse> getProductsByCategory(String category, Pageable pageable) {
        log.info("Fetching products by category: {}", category);
        return productRepository.findByCategoryAndActiveTrue(category, pageable)
            .map(this::toResponse);
    }

    /**
     * Search products by name
     */
    public Page<ProductResponse> searchProducts(String name, Pageable pageable) {
        log.info("Searching products by name: {}", name);
        return productRepository.searchByNameIgnoreCase(name, pageable)
            .map(this::toResponse);
    }

    /**
     * Find products within price range
     */
    public Page<ProductResponse> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        log.info("Finding products in price range: {} - {}", minPrice, maxPrice);
        return productRepository.findByPriceRange(minPrice, maxPrice, pageable)
            .map(this::toResponse);
    }

    /**
     * Get all categories
     */
    public List<String> getAllCategories() {
        log.info("Fetching all product categories");
        return productRepository.findAllCategories();
    }

    /**
     * Create new product
     */
    public ProductResponse createProduct(ProductRequest request) {
        log.info("Creating product with SKU: {}", request.getSku());
        
        if (productRepository.existsBySku(request.getSku())) {
            log.warn("Product SKU already exists: {}", request.getSku());
            throw new RuntimeException("Product with SKU '" + request.getSku() + "' already exists");
        }

        Product product = Product.builder()
            .sku(request.getSku())
            .name(request.getName())
            .description(request.getDescription())
            .price(request.getPrice())
            .stock(request.getStock())
            .category(request.getCategory())
            .active(request.getActive() != null ? request.getActive() : true)
            .build();

        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with ID: {}", savedProduct.getId());
        return toResponse(savedProduct);
    }

    /**
     * Update existing product
     */
    public ProductResponse updateProduct(Long productId, ProductRequest request) {
        log.info("Updating product with ID: {}", productId);
        
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        // Update SKU if provided and different
        if (request.getSku() != null && !request.getSku().equals(product.getSku())) {
            if (productRepository.existsBySku(request.getSku())) {
                throw new RuntimeException("Product with SKU '" + request.getSku() + "' already exists");
            }
            product.setSku(request.getSku());
        }

        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }
        if (request.getStock() != null) {
            product.setStock(request.getStock());
        }
        if (request.getCategory() != null) {
            product.setCategory(request.getCategory());
        }
        if (request.getActive() != null) {
            product.setActive(request.getActive());
        }

        Product updatedProduct = productRepository.save(product);
        log.info("Product updated successfully with ID: {}", updatedProduct.getId());
        return toResponse(updatedProduct);
    }

    /**
     * Delete product (soft delete - set active to false)
     */
    public void deleteProduct(Long productId) {
        log.info("Deleting product with ID: {}", productId);
        
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        product.setActive(false);
        productRepository.save(product);
        log.info("Product soft-deleted with ID: {}", productId);
    }

    /**
     * Check product stock availability
     */
    @Transactional(readOnly = true)
    public boolean checkStock(Long productId, Integer quantity) {
        log.debug("Checking stock for product ID: {}, quantity: {}", productId, quantity);
        
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        return product.hasStock(quantity);
    }

    /**
     * Reserve stock for order (deduct from inventory)
     */
    public void reserveStock(Long productId, Integer quantity) {
        log.info("Reserving stock for product ID: {}, quantity: {}", productId, quantity);
        
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        product.deductStock(quantity);
        productRepository.save(product);
        log.info("Stock reserved successfully for product ID: {}", productId);
    }

    /**
     * Release reserved stock (add back to inventory)
     */
    public void releaseStock(Long productId, Integer quantity) {
        log.info("Releasing stock for product ID: {}, quantity: {}", productId, quantity);
        
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        product.addStock(quantity);
        productRepository.save(product);
        log.info("Stock released successfully for product ID: {}", productId);
    }

    /**
     * Convert Product entity to response DTO
     */
    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .sku(product.getSku())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .stock(product.getStock())
            .category(product.getCategory())
            .active(product.getActive())
            .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .build();
    }
}
