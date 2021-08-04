package com.example.phuotstore.repository;

import com.example.phuotstore.model.Brand;
import com.example.phuotstore.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Boolean existsByCategoryName(String categoryName);

    Boolean existsByCategoryCode(String categoryCode);

    @Query("SELECT ca FROM Category ca WHERE ca.categoryID = ?1")
    Optional<Category> findCategoryByID(Integer categoryID);

    @Query("SELECT ca FROM Category ca")
    Page<Category> getAllCategories(Pageable pageable);

    List<Category> findCategoriesByStatus(String status);

    @Query("SELECT c FROM Category c WHERE c.categoryID = ?1")
    Category getCategoryByID(int brandID);

    @Query("SELECT c FROM Category c WHERE c.status = 'SHOW' OR c.status = 'HIDDEN'")
    List<Category> getAllCategories();

    @Query("SELECT c FROM Category c WHERE c.status = 'SHOW' OR c.status = 'HIDDEN'")
    Page<Category> findPaginateCategoriesStatus(Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.status = 'SHOW' ")
    Page<Category> findPaginateCategoriesStatusShow(Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.status = 'HIDDEN' ")
    Page<Category> findPaginateCategoriesStatusHidden(Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.categoryName = ?1")
    Category findByCategoryName(String categoryName);
}
