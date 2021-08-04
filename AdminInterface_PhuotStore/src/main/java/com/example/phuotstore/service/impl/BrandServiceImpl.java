package com.example.phuotstore.service.impl;

import com.example.phuotstore.model.Brand;
import com.example.phuotstore.repository.BrandRepository;
import com.example.phuotstore.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        try{
            List<Brand> brands = brandRepository.getAllBrands();
            return brands;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Brand> getBrandsByStatus(String status) {
        try{
            List<Brand> brands = brandRepository.findBrandsByStatus(status);
            return brands;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Brand getBrandById(int brandID) {
        try{
            Brand brand = brandRepository.getBrandByID(brandID);
            return brand;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveBrand(Brand brand) {
        try {
            brandRepository.save(brand);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBrand(int brandID) {
        try{
            Brand brand = brandRepository.getBrandByID(brandID);
            brandRepository.delete(brand);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBrand(Brand brand) {
        try {
            brandRepository.save(brand);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkBrandName(String brandName) {
        Brand brand = brandRepository.findByBrandName(brandName);
        if (brand==null)
        {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Page<Brand> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.brandRepository.findPaginateBrandsStatus(pageable);
    }

    @Override
    public Page<Brand> findPaginatedShow(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.brandRepository.findPaginateBrandsStatusShow(pageable);
    }

    @Override
    public Page<Brand> findPaginatedHidden(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.brandRepository.findPaginateBrandsStatusHidden(pageable);
    }
}
