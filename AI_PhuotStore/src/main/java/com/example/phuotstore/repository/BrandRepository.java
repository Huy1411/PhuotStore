package com.example.phuotstore.repository;

import com.example.phuotstore.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    List<Brand> findBrandsByStatus(int status);

    @Query("SELECT c FROM Brand c WHERE c.brandID = ?1")
    Brand getBrandByID(int brandID);

    @Query("SELECT c FROM Brand c WHERE c.status = 1 OR c.status = 2")
    List<Brand> getAllBrands();

    @Query("SELECT c FROM Brand c WHERE c.status = 1 OR c.status = 2")
    Page<Brand> findPaginateBrandsStatus(Pageable pageable);

    @Query("SELECT c FROM Brand c WHERE c.status = 1 ")
    Page<Brand> findPaginateBrandsStatusShow(Pageable pageable);

    @Query("SELECT c FROM Brand c WHERE c.status = 2 ")
    Page<Brand> findPaginateBrandsStatusHidden(Pageable pageable);

    @Query("SELECT c FROM Brand c WHERE c.brandName = ?1")
    Brand findByBrandName(String brandName);
}
