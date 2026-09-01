package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find product by SKU (unique identifier)
     */
    Optional<Product> findBySku(String sku);

    /**
     * Check if SKU already exists
     */
    boolean existsBySku(String sku);

    /**
     * Find products by category
     */
    List<Product> findByCategory(String category);

    /**
     * Find active products by category (paginated)
     */
    Page<Product> findByCategoryAndActiveTrue(String category, Pageable pageable);

    /**
     * Find all active products (paginated)
     */
    Page<Product> findByActiveTrue(Pageable pageable);

    /**
     * Find products by name (partial match)
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) AND p.active = true")
    Page<Product> searchByNameIgnoreCase(@Param("name") String name, Pageable pageable);

    /**
     * Find products within price range
     */
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.active = true")
    Page<Product> findByPriceRange(
        @Param("minPrice") BigDecimal minPrice,
        @Param("maxPrice") BigDecimal maxPrice,
        Pageable pageable
    );

    /**
     * Find products with low stock
     */
    @Query("SELECT p FROM Product p WHERE p.stock < :threshold AND p.active = true")
    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);

    /**
     * Find all categories
     */
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.active = true ORDER BY p.category")
    List<String> findAllCategories();

    /**
     * Find featured products (high stock and active)
     */
    @Query("SELECT p FROM Product p WHERE p.stock >= :minStock AND p.active = true ORDER BY p.createdAt DESC")
    Page<Product> findFeaturedProducts(@Param("minStock") Integer minStock, Pageable pageable);
}
