package com.SpringBootJava.productlist.service;


import com.SpringBootJava.productlist.dto.CategoryDTO;
import com.SpringBootJava.productlist.entity.Category;
import com.SpringBootJava.productlist.exception.CategoryAlreadyExistsException;
import com.SpringBootJava.productlist.mapper.CategoryMapper;
import com.SpringBootJava.productlist.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {


    private CategoryRepository categoryRepository;

    //create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());
        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyExistsException("Category "
                    + categoryDTO.getName() + " already Exists!");
        }
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);

    }
    // get all categories
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }


    // get category by id
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));
        return CategoryMapper.toCategoryDTO(category);

    }
    // delete category
    public String deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return "Category "+id+ " has been deleted";
    }
}
