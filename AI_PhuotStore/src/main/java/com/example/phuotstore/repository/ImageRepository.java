package com.example.phuotstore.repository;

import com.example.phuotstore.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findImagesByStatus(int status);

    @Query("SELECT p FROM Image p WHERE p.product.productID = ?1")
    List<Image> findImagesByProduct(int productID);

    @Query("SELECT p FROM Image p WHERE p.status = 1 OR p.status = 2")
    List<Image> getAllImages();

    @Query("SELECT p FROM Image p WHERE p.status = 1 OR p.status = 2")
    Page<Image> findPaginateImagesStatus(Pageable pageable);

    @Query("SELECT p FROM Image p WHERE p.status = 1 ")
    Page<Image> findPaginateImagesStatusShow(Pageable pageable);

    @Query("SELECT p FROM Image p WHERE p.status = 2 ")
    Page<Image> findPaginateImagesStatusHidden(Pageable pageable);

    @Query("SELECT p FROM Image p WHERE p.imgName = ?1 AND p.product.productID = ?2")
    Image findByImageName(String imageName,int productID);
}
