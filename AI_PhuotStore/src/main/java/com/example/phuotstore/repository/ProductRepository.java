package com.example.phuotstore.repository;

import com.example.phuotstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p")
    List<Product> getAllProducts();

    @Query("SELECT p FROM Product p WHERE p.productID = ?1")
    Product findProductByID(Product productID);

    @Query("SELECT p FROM Product p WHERE p.brand.brandID = ?1 AND p.brand.status= 1 AND p.status = 1")
    Page<Product> getProductsByBrandID(int brandID, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.categoryID = ?1 AND p.brand.status= 1 AND p.status = 1")
    Page<Product> getProductsByCategoryID(int categoryID, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = 1 OR p.status = 2")
    List<Product> getProductsByStatus(int status);

    @Query("SELECT p FROM Product p WHERE p.status = 1 OR p.status = 2")
    Page<Product> findPaginateProductsStatus(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = 1 ")
    Page<Product> findPaginateProductsStatusShow(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = 2 ")
    Page<Product> findPaginateProductsStatusHidden(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.productName = ?1 AND p.brand.brandID = ?2 AND p.category.categoryID = ?3")
    Product findByProductName(String productName,int brandID, int categoryID);
}
