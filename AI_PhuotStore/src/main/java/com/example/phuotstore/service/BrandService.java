package com.example.phuotstore.service;

import com.example.phuotstore.model.Brand;
import org.springframework.data.domain.Page;
import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();
    List<Brand> getBrandsByStatus(int status);
    Brand getBrandById(int brandID);
    boolean saveBrand(Brand brand);
    boolean deleteBrand(int brandID);
    boolean updateBrand(Brand brand);
    boolean checkBrandName(String brandName);
    Page<Brand> findPaginated(int pageNo, int pageSize);
    Page<Brand> findPaginatedShow(int pageNo,int pageSize);
    Page<Brand> findPaginatedHidden(int pageNo,int pageSize);
}
