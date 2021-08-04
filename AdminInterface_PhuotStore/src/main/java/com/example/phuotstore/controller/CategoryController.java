package com.example.phuotstore.controller;


import com.example.phuotstore.model.Brand;
import com.example.phuotstore.model.Category;
import com.example.phuotstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getAllCategories(Model model){
        Category category = new Category();
        return findPaginated(1, model, category);
    }

    @RequestMapping("show")
    public String getCategoriesShow(Model model) {
        Category category = new Category();
        return findPagShow(1, model, category);
    }

    @RequestMapping("hidden")
    public String getCategoriesHidden(Model model) {
        Category category = new Category();
        return findPagHidden(1, model, category);
    }

    @RequestMapping(path = "/saveCategory", method = RequestMethod.POST)
    public String saveLCategory(@ModelAttribute("newCategory") @Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return findPaginated(1, model, category);
        }
        boolean checkCategoryName = categoryService.checkCategoryName(category.getCategoryName());
        if (checkCategoryName == false) {
            return "redirect:/admin/category?errorcategoryname=Category Name is existed";
        }

        boolean bl = categoryService.saveCategory(category);
        if (bl) {
            return "redirect:/admin/category/";
        }
        return "redirect:/admin/category?error=Add New Category error";

    }

    @RequestMapping(path = "/editCategory")
    public String editCategory(@RequestParam("id") Integer categoryID, Model model) {
        Category category = categoryService.getCategoryByID(categoryID);
        model.addAttribute("editCategory", category);
        return "admin/category/editCategory";
    }

    @RequestMapping(path = "/updateCategory", method = RequestMethod.POST)
    public String updateCategory(@ModelAttribute("editCategory") Category category) {
        boolean checkCategoryName = checkCategoryName(category.getCategoryName(), category.getCategoryID());
        if (checkCategoryName == false) {
            return "redirect:/admin/category/editCategory?id=" + category.getCategoryID() + "&&errorcategoryName=Category Name is existed";
        }
        boolean bl = categoryService.updateCategory(category);
        if (bl) {
            return "redirect:/admin/category/";
        }
        return "redirect:/admin/category?error=Update Category error";

    }

    @RequestMapping(value = "/detailCategory")
    public String detailCategoryById(@RequestParam("id") Integer categoryID, Model model) {
        Category category = categoryService.getCategoryByID(categoryID);
        model.addAttribute("detailCategory", category);
        return "admin/category/detailCategory";
    }

    @RequestMapping(path = "/deleteCategory")
    public String deleteCategory(@RequestParam("id") Integer id) {
        boolean bl = categoryService.deleteCategory(id);
        if (bl) {
            return "redirect:/admin/category/";
        }
        return "redirect:admin/category?error=Delete Category error";
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, Category category) {
        int pageSize = 10;
        Page<Category> page = categoryService.findPaginated(pageNo, pageSize);
        List<Category> categories = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newCategory", category);
        model.addAttribute("categories", categories);
        return "admin/category/listCategory";
    }

    @RequestMapping("/pageShow/{pageNo}")
    public String findPagShow(@PathVariable(value = "pageNo") int pageNo, Model model, Category category) {
        int pageSize = 10;
        Page<Category> page = categoryService.findPaginatedShow(pageNo, pageSize);
        List<Category> categories = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newCategory", category);
        model.addAttribute("categories", categories);
        return "admin/category/listCategory";
    }

    @RequestMapping("/pageHidden/{pageNo}")
    public String findPagHidden(@PathVariable(value = "pageNo") int pageNo, Model model, Category category) {
        int pageSize = 10;
        Page<Category> page = categoryService.findPaginatedHidden(pageNo, pageSize);
        List<Category> categories = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newCategory", category);
        model.addAttribute("categories", categories);
        return "admin/category/listCategory";
    }

    public boolean checkCategoryName(String categoryName, int categoryID) {
        Category category = categoryService.getCategoryByID(categoryID);
        boolean checkCategoryName = categoryService.checkCategoryName(categoryName);
        if (checkCategoryName == false) {
            if (categoryName.equals(category.getCategoryName())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
