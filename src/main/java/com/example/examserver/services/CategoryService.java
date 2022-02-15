package com.example.examserver.services;

import com.example.examserver.entities.exam.Category;
import com.example.examserver.interfaces.services.CategoryServiceInterface;
import com.example.examserver.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category) {
        Category category1=categoryRepository.findById(category.getId()).get();

        return category1!=null?categoryRepository.save(category):null;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
