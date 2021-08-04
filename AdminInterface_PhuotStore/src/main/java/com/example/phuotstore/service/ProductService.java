package com.example.phuotstore.service;

import com.example.phuotstore.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByStatus(int status);
    Product getByProductName(String productName);

    Page<Product> getProductsByBrand(int brandID, int pageNo, int pageSize);
    Page<Product> getProductsByCategory(int categoryID, int pageNo, int pageSize);
    Product getProductByID(int productID);
    boolean saveProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int productID);
    Page<Product> findPaginated(int pageNo, int pageSize);
    Page<Product> findPaginatedShow(int pageNo,int pageSize);
    Page<Product> findPaginatedHidden(int pageNo,int pageSize);
    boolean checkProductName(String productName,int brandID, int categoryID);
}
