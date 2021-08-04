package com.example.phuotstore.service.impl;

import com.example.phuotstore.model.Brand;
import com.example.phuotstore.model.Category;
import com.example.phuotstore.repository.CategoryRepository;
import com.example.phuotstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoriesByStatus(String status) {
        try {
            List<Category> categories = categoryRepository.findCategoriesByStatus(status);
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.getAllCategories();
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category getCategoryByID(int categoryID) {
        try {
            Category category = categoryRepository.getCategoryByID(categoryID);
            return category;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveCategory(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCategory(int categoryID) {
        try {
            Category category = categoryRepository.getCategoryByID(categoryID);
            categoryRepository.delete(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCategory(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkCategoryName(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if (category == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<Category> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.categoryRepository.findPaginateCategoriesStatus(pageable);
    }

    @Override
    public Page<Category> findPaginatedShow(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.categoryRepository.findPaginateCategoriesStatusShow(pageable);
    }

    @Override
    public Page<Category> findPaginatedHidden(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.categoryRepository.findPaginateCategoriesStatusHidden(pageable);
    }
}
