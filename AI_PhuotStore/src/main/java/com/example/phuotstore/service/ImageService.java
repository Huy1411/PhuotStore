package com.example.phuotstore.service;

import com.example.phuotstore.model.Image;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ImageService {
    List<Image> getAllImages();
    List<Image> getImagesByStatus(int status);
    List<Image> getImagesByProduct(int productID);
    Image getImageById(int imageID);
    boolean saveImage(Image image);
    boolean deleteImage(int imageID);
    boolean updateImage(Image image);
    Page<Image> findPaginated(int pageNo, int pageSize);
    Page<Image> findPaginatedShow(int pageNo,int pageSize);
    Page<Image> findPaginatedHidden(int pageNo,int pageSize);
    boolean checkImageName(String imageName,int productID);
}
