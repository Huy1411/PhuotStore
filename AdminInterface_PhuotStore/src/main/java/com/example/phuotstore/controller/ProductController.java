package com.example.phuotstore.controller;

import com.example.phuotstore.model.Brand;
import com.example.phuotstore.model.Category;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.service.BrandService;
import com.example.phuotstore.service.CategoryService;
import com.example.phuotstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

        @Autowired
        private CategoryService categoryService;

        @Autowired
        private BrandService brandService;


        @InitBinder
        public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getProducts(Model model) {
        Product product = new Product();
        return findPaginated(1, model, product);
    }

    @RequestMapping("show")
    public String getProductShow(Model model) {
        Product product = new Product();
        return findPagShow(1, model, product);
    }

    @RequestMapping("hidden")
    public String getProductHidden(Model model) {
        Product product = new Product();
        return findPagHidden(1, model, product);
    }

    @RequestMapping("/insertProduct")
    public String insertProduct(Model model) {
        Product product = new Product();
        List<Category> categories = categoryService.getCategoriesByStatus("SHOW");
        List<Brand> brands = brandService.getBrandsByStatus("SHOW");
        model.addAttribute("newProduct", product);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        return "admin/product/insertProduct";
    }

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("newProduct") @Valid Product product, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            List<Brand> brands = brandService.getAllBrands();
            model.addAttribute("categories", categories);
            model.addAttribute("brands", brands);
            model.addAttribute("newProduct", product);
            return "admin/product/insertProduct";
        }
        boolean checkProductName = productService.checkProductName(product.getProductName(), product.getBrand().getBrandID(), product.getCategory().getCategoryID());
        if (checkProductName == false) {
            return "redirect:/admin/produt/insertProduct?errorproductname=Product Name is existed";
        }

        boolean bl = productService.saveProduct(product);
        if (bl) {
            return "redirect:/admin/product/";
        }

        product.setCreateAt(new Date());
        product.setUpdateAt(new Date());
        return "redirect:/admin/product?error=Add New Product error";
    }

    @RequestMapping(value = "/editProduct")
    public String editProduct(@RequestParam("id") Integer productID, Model model) {
        Product product = productService.getProductByID(productID);
        List<Category> categories = categoryService.getCategoriesByStatus("SHOW");
        List<Brand> brands = brandService.getBrandsByStatus("SHOW");
        model.addAttribute("editProduct", product);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        return "admin/product/editProduct";
    }

    @RequestMapping(path = "/updateProduct", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("editProduct") Product product) {
        boolean checkProductName = checkProductName(product.getProductName(), product.getProductID(), product.getBrand().getBrandID(), product.getCategory().getCategoryID());
        if (checkProductName == false) {
            return "redirect:/admin/product/editProduct?id=" + product.getProductID() + "&&errorproductname=Product Name is existed";
        }
        boolean bl = productService.updateProduct(product);
        if (bl) {
            return "redirect:/admin/product/";
        }
        product.setUpdateAt(new Date());
        return "redirect:/admin/product?error=Update Product Error";

    }

    @RequestMapping(value = "/detailProduct")
    public String detailProductById(@RequestParam("id") Integer productID, Model model) {
        Product product = productService.getProductByID(productID);
        model.addAttribute("detailProduct", product);
        return "admin/product/detailProduct";
    }

    @RequestMapping(path = "/deleteProduct")
    public String deleteProduct(@RequestParam("id") Integer productID) {
        boolean bl = productService.deleteProduct(productID);
        if (bl) {
            return "redirect:/admin/product/";
        }
        return "redirect:admin/product?error=Delete Product Error";
    }


    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, Product product) {
        int pageSize = 10;
        List<Brand> brands = brandService.getAllBrands();
        List<Category> categories = categoryService.getAllCategories();
        Page<Product> page = productService.findPaginated(pageNo, pageSize);
        List<Product> products = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newProduct", product);
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        return "admin/product/listProduct";
    }

    @RequestMapping("/pageShow/{pageNo}")
    public String findPagShow(@PathVariable(value = "pageNo") int pageNo, Model model, Product product) {
        int pageSize = 10;
        List<Brand> brands = brandService.getAllBrands();
        List<Category> categories = categoryService.getAllCategories();
        Page<Product> page = productService.findPaginatedShow(pageNo, pageSize);
        List<Product> products = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newProduct", product);
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        return "admin/product/listProduct";
    }

    @RequestMapping("/pageHidden/{pageNo}")
    public String findPagHidden(@PathVariable(value = "pageNo") int pageNo, Model model, Product product) {
        int pageSize = 10;
        List<Brand> brands = brandService.getAllBrands();
        List<Category> categories = categoryService.getAllCategories();
        Page<Product> page = productService.findPaginatedHidden(pageNo, pageSize);
        List<Product> products = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newProduct", product);
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        return "admin/product/listProduct";
    }

    public boolean checkProductName(String productName, int productID, int brandID, int categoryID) {
        Product product = productService.getProductByID(productID);
        boolean checkProductName = productService.checkProductName(productName, brandID, categoryID);
        if (checkProductName == false) {
            if (productName.equals(product.getProductName()) && product.getBrand().getBrandID() == brandID && product.getCategory().getCategoryID() == categoryID) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }


}
