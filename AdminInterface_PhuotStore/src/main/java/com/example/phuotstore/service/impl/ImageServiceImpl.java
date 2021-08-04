package com.example.phuotstore.service.impl;

import com.example.phuotstore.model.Image;
import com.example.phuotstore.repository.ImageRepository;
import com.example.phuotstore.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAllImages() {
        return null;
    }

    @Override
    public List<Image> getImagesByStatus(int status) {
        try {
            List<Image> images = imageRepository.findImagesByStatus(status);
            return images;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Image> getImagesByProduct(int productID) {
        try {
            List<Image> images = imageRepository.findImagesByProduct(productID);
            return images;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Image getImageById(int imageID) {
        try {
            Image image = imageRepository.findById(imageID).get();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveImage(Image image) {
        try {
            imageRepository.save(image);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteImage(int imageID) {
        try {
            Image image = imageRepository.findById(imageID).get();
            imageRepository.delete(image);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateImage(Image image) {
        try {
            imageRepository.save(image);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<Image> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.imageRepository.findPaginateImagesStatus(pageable);
    }

    @Override
    public Page<Image> findPaginatedShow(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.imageRepository.findPaginateImagesStatusShow(pageable);
    }

    @Override
    public Page<Image> findPaginatedHidden(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.imageRepository.findPaginateImagesStatusHidden(pageable);
    }

    @Override
    public boolean checkImageName(String imageName, int productID) {
        Image image = imageRepository.findByImageName(imageName,productID);
        if (image==null)
        {
            return true;
        }else{
            return false;
        }
    }
}
