package com.example.examserver.interfaces.services;

import com.example.examserver.entities.exam.Category;

import java.util.List;

public interface CategoryServiceInterface {
    public Category addCategory(Category category);
    public Category getCategoryById(Long id);
    public List<Category> getCategories();
    public Category updateCategory(Category category);
    public void deleteCategory(Long id);
    
}
