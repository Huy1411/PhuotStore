package com.example.phuotstore.repository;

import com.example.phuotstore.model.Brand;
import com.example.phuotstore.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoriesByStatus(int status);

    @Query("SELECT c FROM Category c WHERE c.categoryID = ?1")
    Category getCategoryByID(int brandID);

    @Query("SELECT c FROM Category c WHERE c.status = 1 OR c.status = 2")
    List<Category> getAllCategories();

    @Query("SELECT c FROM Category c WHERE c.status = 1 OR c.status = 2")
    Page<Category> findPaginateCategoriesStatus(Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.status = 1 ")
    Page<Category> findPaginateCategoriesStatusShow(Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.status = 2 ")
    Page<Category> findPaginateCategoriesStatusHidden(Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.categoryName = ?1")
    Category findByCategoryName(String categoryName);
}
