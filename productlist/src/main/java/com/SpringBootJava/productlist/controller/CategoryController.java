package com.SpringBootJava.productlist.controller;

import com.SpringBootJava.productlist.dto.CategoryDTO;
import com.SpringBootJava.productlist.exception.CategoryAlreadyExistsException;
import com.SpringBootJava.productlist.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name="Category REST API CRUD operation",
        description = "CREATE, READ, UPDATE AND DELETE operations for Category REST API"
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    // get all Categories
    @Operation(
            summary = "Fetch all Categories",
            description = "REST API to fetch all categories along with their products."
    )
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // createCategory
    @Operation(
            summary = "Create Category",
            description = "REST API to create category."
    )

    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // get category by id
    @Operation(
            summary = "Fetch Category by categoryId",
            description = "REST API to fetch category by categoryId."
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);

    }

    // delete category
    @Operation(
            summary = "Delete Category by categoryId",
            description = "REST API to delete category by categoryId."
    )
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
