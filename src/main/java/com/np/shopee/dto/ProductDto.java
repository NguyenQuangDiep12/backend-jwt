package com.np.shopee.dto;

import java.math.BigDecimal;
import java.util.List;

import com.np.shopee.model.Category;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;

    private Category category;

    private List<ImageDto> images;
}
