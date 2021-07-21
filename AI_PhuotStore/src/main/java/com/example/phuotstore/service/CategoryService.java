package com.example.phuotstore.service;

import com.example.phuotstore.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Category> getCategoriesByStatus(int status);
    Category getCategoryById(int brandID);
    boolean saveCategory(Category category);
    boolean deleteCategory(int categoryID);
    boolean updateCategory(Category category);
    boolean checkCategoryName(String categoryName);
    Page<Category> findPaginated(int pageNo, int pageSize);
    Page<Category> findPaginatedShow(int pageNo,int pageSize);
    Page<Category> findPaginatedHidden(int pageNo,int pageSize);
}
