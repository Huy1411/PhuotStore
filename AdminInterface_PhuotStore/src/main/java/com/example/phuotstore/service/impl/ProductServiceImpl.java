package com.example.phuotstore.service.impl;

import com.example.phuotstore.model.Product;
import com.example.phuotstore.repository.ProductRepository;
import com.example.phuotstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        try{
            List<Product> products = productRepository.getAllProducts();
            return products;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getProductsByStatus(int status) {
        try{
            List<Product> products = productRepository.getProductsByStatus(status);
            return products;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getByProductName(String productName) {
        try{

            Product product = productRepository.getByProductName(productName);
            return product;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Product> getProductsByBrand(int brandID, int pageNo, int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<Product> products = productRepository.getProductsByBrandID(brandID, pageable);
            return products;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Product> getProductsByCategory(int categoryID, int pageNo, int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo-1,pageSize);
            Page<Product> products = productRepository.getProductsByCategoryID(categoryID, pageable);
            return products;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getProductByID(int productID) {
        try{

            Product product = productRepository.findById(productID).get();

            return product;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveProduct(Product product) {
        try {

            productRepository.save(product);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int productID) {
        try{
            Product product = productRepository.findById(productID).get();
            productRepository.delete(product);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.productRepository.findPaginateProductsStatus(pageable);
    }

    @Override
    public Page<Product> findPaginatedShow(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.productRepository.findPaginateProductsStatusShow(pageable);
    }

    @Override
    public Page<Product> findPaginatedHidden(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.productRepository.findPaginateProductsStatusHidden(pageable);
    }

    @Override
    public boolean checkProductName(String productName, int brandID, int categoryID) {
        Product product = productRepository.findByProductName(productName,brandID, categoryID);
        if (product==null)
        {
            return true;
        }else{
            return false;
        }
    }
}
