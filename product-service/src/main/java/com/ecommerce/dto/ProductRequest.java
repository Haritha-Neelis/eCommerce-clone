package com.ecommerce.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    private String sku;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private String category;

    private Boolean active;
}
