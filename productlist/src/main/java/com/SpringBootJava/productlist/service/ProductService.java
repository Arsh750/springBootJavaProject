package com.SpringBootJava.productlist.service;

import com.SpringBootJava.productlist.dto.ProductDTO;
import com.SpringBootJava.productlist.entity.Category;
import com.SpringBootJava.productlist.entity.Product;
import com.SpringBootJava.productlist.mapper.ProductMapper;
import com.SpringBootJava.productlist.repository.CategoryRepository;
import com.SpringBootJava.productlist.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        // DTO -> entity
        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);

        // Entity -> DTO
        return ProductMapper.toProductDTO(product);

    }
    // getAllProducts
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }
    // Get Product by id
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        return ProductMapper.toProductDTO(product);

    }
    // Update Product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }
    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product "+ id + " has been deleted";
    }

}