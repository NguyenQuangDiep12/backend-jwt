package com.np.shopee.request;

import java.math.BigDecimal;

import com.np.shopee.model.Category;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;

    private Category category;
}
