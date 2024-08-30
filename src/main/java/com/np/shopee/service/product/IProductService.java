package com.np.shopee.service.product;

import java.util.List;

import com.np.shopee.model.Product;
import com.np.shopee.request.AddProductRequest;

public interface IProductService {
    void addProduct(AddProductRequest product);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String brand, String name);

    List<Product> getProductsByBrand(String brand);

    Long countProductsByBrandAndName(String brand, String name);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    void updateProduct(Product product, Long productId);

}
