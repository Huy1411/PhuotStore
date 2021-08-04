package com.example.phuotstore.repository;

import com.example.phuotstore.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {


    //API
    @Query("SELECT img FROM Image img WHERE img.imgID = ?1")
    Optional<Image> findImageByID(int imgID);

    @Query("SELECT img FROM Image img WHERE img.product.productID =?1")
    Page<Image> findImagesByProductID(Integer productID, Pageable pageable);

    @Query("SELECT img FROM Image img")
    Page<Image> getAllImages(Pageable pageable);


    //MVC
    List<Image> findImagesByStatus(int status);

    @Query("SELECT p FROM Image p WHERE p.product.productID = ?1")
    List<Image> findImagesByProduct(int productID);

    @Query("SELECT p FROM Image p WHERE p.status = 'SHOW' OR p.status = 'HIDDEN'")
    List<Image> getImages();

    @Query("SELECT p FROM Image p WHERE p.status = 'SHOW' OR p.status = 'HIDDEN'")
    Page<Image> findPaginateImagesStatus(Pageable pageable);

    @Query("SELECT p FROM Image p WHERE p.status = 'SHOW' ")
    Page<Image> findPaginateImagesStatusShow(Pageable pageable);

    @Query("SELECT p FROM Image p WHERE p.status = 'HIDDEN' ")
    Page<Image> findPaginateImagesStatusHidden(Pageable pageable);

    @Query("SELECT p FROM Image p WHERE p.imgName = ?1 AND p.product.productID = ?2")
    Image findByImageName(String imageName,int productID);
}
