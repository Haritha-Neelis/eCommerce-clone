package com.ecommerce.controller;

import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    /**
     * GET /api/products - Get all active products with pagination
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("GET /api/products - Fetching all products");
        
        Page<ProductResponse> products = productService.getAllProducts(pageable);
        
        Map<String, Object> response = new HashMap<>();
        response.put("data", products.getContent());
        response.put("page", products.getNumber());
        response.put("size", products.getSize());
        response.put("total_elements", products.getTotalElements());
        response.put("total_pages", products.getTotalPages());
        response.put("has_next", products.hasNext());
        response.put("has_previous", products.hasPrevious());
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/products/{id} - Get single product by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        log.info("GET /api/products/{} - Fetching single product", id);
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * GET /api/products/sku/{sku} - Get product by SKU
     */
    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductResponse> getProductBySku(@PathVariable String sku) {
        log.info("GET /api/products/sku/{} - Fetching product by SKU", sku);
        ProductResponse product = productService.getProductBySku(sku);
        return ResponseEntity.ok(product);
    }

    /**
     * GET /api/products/category/{category} - Get products by category
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<Map<String, Object>> getProductsByCategory(
            @PathVariable String category,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("GET /api/products/category/{} - Fetching products by category", category);
        
        Page<ProductResponse> products = productService.getProductsByCategory(category, pageable);
        
        Map<String, Object> response = new HashMap<>();
        response.put("data", products.getContent());
        response.put("category", category);
        response.put("page", products.getNumber());
        response.put("size", products.getSize());
        response.put("total_elements", products.getTotalElements());
        response.put("total_pages", products.getTotalPages());
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/products/search - Search products by name
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchProducts(
            @RequestParam String name,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("GET /api/products/search?name={} - Searching products", name);
        
        Page<ProductResponse> products = productService.searchProducts(name, pageable);
        
        Map<String, Object> response = new HashMap<>();
        response.put("data", products.getContent());
        response.put("search_query", name);
        response.put("page", products.getNumber());
        response.put("size", products.getSize());
        response.put("total_elements", products.getTotalElements());
        response.put("total_pages", products.getTotalPages());
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/products/price-range - Filter by price range
     */
    @GetMapping("/price-range")
    public ResponseEntity<Map<String, Object>> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice,
            @PageableDefault(size = 20, sort = "price", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("GET /api/products/price-range?minPrice={}&maxPrice={}", minPrice, maxPrice);
        
        Page<ProductResponse> products = productService.findProductsByPriceRange(minPrice, maxPrice, pageable);
        
        Map<String, Object> response = new HashMap<>();
        response.put("data", products.getContent());
        response.put("min_price", minPrice);
        response.put("max_price", maxPrice);
        response.put("page", products.getNumber());
        response.put("size", products.getSize());
        response.put("total_elements", products.getTotalElements());
        response.put("total_pages", products.getTotalPages());
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/products/categories - Get all available categories
     */
    @GetMapping("/categories")
    public ResponseEntity<Map<String, Object>> getAllCategories() {
        log.info("GET /api/products/categories - Fetching all categories");
        
        List<String> categories = productService.getAllCategories();
        
        Map<String, Object> response = new HashMap<>();
        response.put("data", categories);
        response.put("count", categories.size());
        
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/products - Create new product (Admin only)
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        log.info("POST /api/products - Creating new product with SKU: {}", request.getSku());
        
        ProductResponse product = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    /**
     * PUT /api/products/{id} - Update product (Admin only)
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request) {
        log.info("PUT /api/products/{} - Updating product", id);
        
        ProductResponse product = productService.updateProduct(id, request);
        return ResponseEntity.ok(product);
    }

    /**
     * DELETE /api/products/{id} - Delete product (soft delete, Admin only)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        log.info("DELETE /api/products/{} - Deleting product", id);
        
        productService.deleteProduct(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product deleted successfully");
        response.put("product_id", id.toString());
        
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/products/{id}/check-stock - Check if product has stock
     */
    @PostMapping("/{id}/check-stock")
    public ResponseEntity<Map<String, Object>> checkStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        log.info("POST /api/products/{}/check-stock?quantity={}", id, quantity);
        
        boolean hasStock = productService.checkStock(id, quantity);
        
        Map<String, Object> response = new HashMap<>();
        response.put("product_id", id);
        response.put("quantity_requested", quantity);
        response.put("has_stock", hasStock);
        
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/products/{id}/reserve - Reserve stock for order
     */
    @PostMapping("/{id}/reserve")
    public ResponseEntity<Map<String, String>> reserveStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        log.info("POST /api/products/{}/reserve?quantity={}", id, quantity);
        
        productService.reserveStock(id, quantity);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Stock reserved successfully");
        response.put("product_id", id.toString());
        response.put("quantity_reserved", quantity.toString());
        
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/products/{id}/release - Release reserved stock
     */
    @PostMapping("/{id}/release")
    public ResponseEntity<Map<String, String>> releaseStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        log.info("POST /api/products/{}/release?quantity={}", id, quantity);
        
        productService.releaseStock(id, quantity);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Stock released successfully");
        response.put("product_id", id.toString());
        response.put("quantity_released", quantity.toString());
        
        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "product-service");
        return ResponseEntity.ok(response);
    }
}
