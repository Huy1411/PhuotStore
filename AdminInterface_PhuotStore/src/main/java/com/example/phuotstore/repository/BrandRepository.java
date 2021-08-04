package com.example.phuotstore.repository;

import com.example.phuotstore.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {


    Boolean existsByBrandName(String brandName);

    Boolean existsByBrandCode(String BrandCode);

    @Query("SELECT br FROM Brand br WHERE br.brandID = ?1")
    Optional<Brand> findBrandByID(Integer brandID);

    @Query("SELECT br FROM Brand br")
    Page<Brand> getAllBrands(Pageable pageable);

    List<Brand> findBrandsByStatus(String status);

    @Query("SELECT c FROM Brand c WHERE c.brandID = ?1")
    Brand getBrandByID(int brandID);

    @Query("SELECT c FROM Brand c WHERE c.status = 'SHOW' OR c.status = 'HIDDEN'")
    List<Brand> getAllBrands();

    @Query("SELECT c FROM Brand c WHERE c.status = 'SHOW' OR c.status = 'HIDDEN'")
    Page<Brand> findPaginateBrandsStatus(Pageable pageable);

    @Query("SELECT c FROM Brand c WHERE c.status = 'SHOW' ")
    Page<Brand> findPaginateBrandsStatusShow(Pageable pageable);

    @Query("SELECT c FROM Brand c WHERE c.status = 'HIDDEN' ")
    Page<Brand> findPaginateBrandsStatusHidden(Pageable pageable);

    @Query("SELECT c FROM Brand c WHERE c.brandName = ?1")
    Brand findByBrandName(String brandName);
}

