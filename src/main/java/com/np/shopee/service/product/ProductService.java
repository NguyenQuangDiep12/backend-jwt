package com.np.shopee.service.product;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.np.shopee.dto.ImageDto;
import com.np.shopee.dto.ProductDto;
import com.np.shopee.exception.ProductNotFoundException;
import com.np.shopee.model.Category;
import com.np.shopee.model.Image;
import com.np.shopee.model.Product;
import com.np.shopee.repository.CategoryRepository;
import com.np.shopee.repository.ImageRepository;
import com.np.shopee.repository.ProductRepository;
import com.np.shopee.request.AddProductRequest;
import com.np.shopee.request.ProductUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    // addProduct
    @Override
    public Product addProduct(AddProductRequest request) {
        // check if the category is found in the DB
        // If Yes , set it as the new product category
        // If No , the save it as a new category
        // the set as the new product category
        // Optional
        Category category = Optional.ofNullable(categoryRepository.findByname(request.getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(), request.getBrand(), request.getDescription(), request.getPrice(),
                request.getInventory(), category);
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setCategory(request.getCategory());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setPrice(request.getPrice());
        Category category = categoryRepository.findByname(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete, () -> {
                    throw new ProductNotFoundException("Product not found! ");
                });
    }

    @Override
    public List<ProductDto> getConvertProducts(List<Product> products) {
        return products
                .stream()
                .map(product -> convertToDto(product))
                .toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images
                .stream()
                .map(image -> modelMapper.map(image, ImageDto.class))
                .toList();

        productDto.setImages(imageDtos);
        return productDto;
    }

}
