package com.example.phuotstore.repository;

import com.example.phuotstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    //API
    Boolean existsByProductName(String  productName);

    Boolean existsByProductCode(String productCode);

    @Query("SELECT p FROM Product p WHERE p.productID = ?1")
    Product findByID(int productID);

    @Query("SELECT p FROM Product p WHERE p.productID = ?1")
    Optional<Product> findProductByID(int productID);

    @Query("SELECT p FROM Product p WHERE p.category.categoryID = ?1")
    Page<Product> findProductsByCategoryID(int categoryID, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.brand.brandID = ?1")
    Page<Product> findProductsByBrandID(int brandID, Pageable pageable);

    //MVC
    @Query("SELECT p FROM Product p")
    Page<Product> getAllProducts(Pageable pageable);

    @Query("SELECT p FROM Product p")
    List<Product> getAllProducts();

    @Query("SELECT p FROM Product p WHERE p.productName = ?1")
    Product getByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE p.brand.brandID = ?1 AND p.brand.status= 'SHOW' AND p.status = 'SHOW'")
    Page<Product> getProductsByBrandID(int brandID, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.categoryID = ?1 AND p.category.status= 'SHOW' AND p.status = 'SHOW'")
    Page<Product> getProductsByCategoryID(int categoryID, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = 'SHOW' OR p.status = 'HIDDEN'")
    List<Product> getProductsByStatus(int status);

    @Query("SELECT p FROM Product p WHERE p.status = 'SHOW' OR p.status = 'HIDDEN'")
    Page<Product> findPaginateProductsStatus(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = 'SHOW' ")
    Page<Product> findPaginateProductsStatusShow(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = 'HIDDEN' ")
    Page<Product> findPaginateProductsStatusHidden(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.productName = ?1 AND p.brand.brandID = ?2 AND p.category.categoryID = ?3")
    Product findByProductName(String productName,int brandID, int categoryID);

}
