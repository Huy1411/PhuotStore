package com.example.phuotstore.controller;


import com.example.phuotstore.model.Brand;
import com.example.phuotstore.service.BrandService;
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
@RequestMapping(path = "/admin/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getAllBrands(Model model) {
        Brand brand = new Brand();
        return findPaginated(1, model, brand);
    }

    @RequestMapping("show")
    public String getBrandsShow(Model model) {
        Brand brand = new Brand();
        return findPagShow(1, model, brand);
    }

    @RequestMapping("hidden")
    public String getBrandsHidden(Model model) {
        Brand brand = new Brand();
        return findPagHidden(1, model, brand);
    }

    @RequestMapping(path = "/saveBrand", method = RequestMethod.POST)
    public String saveBrand(@ModelAttribute("newBrand") @Valid Brand brand, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return findPaginated(1, model, brand);
        }
        boolean checkBrandName = brandService.checkBrandName(brand.getBrandName());
        if (checkBrandName == false) {
            return "redirect:/admin/brand?errorbrandname=BrandName is existed";
        }

        boolean bl = brandService.saveBrand(brand);
        if (bl) {
            return "redirect:/admin/brand/";
        }
        return "redirect:/admin/brand?error=Add New Brand error";

    }

    @RequestMapping(path = "/editBrand")
    public String editBrand(@RequestParam("id") Integer id, Model model) {
        Brand brand = brandService.getBrandById(id);
        model.addAttribute("editBrand", brand);
        return "admin/brand/editBrand";
    }

    @RequestMapping(path = "/updateBrand", method = RequestMethod.POST)
    public String updateBrand(@ModelAttribute("editBrand") Brand brand) {
        boolean checkBrandName = checkBrandName(brand.getBrandName(), brand.getBrandID());
        if (checkBrandName == false) {
            return "redirect:/admin/brand/editBrand?id=" + brand.getBrandID() + "&&errorbrandName=Brand Name is existed";
        }
        boolean bl = brandService.updateBrand(brand);
        if (bl) {
            return "redirect:/admin/brand/";
        }
        return "redirect:/admin/brand?error=Update Brand error";

    }

    @RequestMapping(path = "/deleteBrand")
    public String deleteBrand(@RequestParam("id") Integer id) {
        boolean bl = brandService.deleteBrand(id);
        if (bl) {
            return "redirect:/admin/brand/";
        }
        return "redirect:admin/brand?error=Delete Brand error";
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, Brand brand) {
        int pageSize = 10;
        Page<Brand> page = brandService.findPaginated(pageNo, pageSize);
        List<Brand> brands = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newBrand", brand);
        model.addAttribute("brands", brands);
        return "admin/brand/listBrand";
    }

    @RequestMapping("/pageShow/{pageNo}")
    public String findPagShow(@PathVariable(value = "pageNo") int pageNo, Model model, Brand brand) {
        int pageSize = 10;
        Page<Brand> page = brandService.findPaginatedShow(pageNo, pageSize);
        List<Brand> brands = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newBrand", brand);
        model.addAttribute("brands", brands);
        return "admin/brand/listBrand";
    }

    @RequestMapping("/pageHidden/{pageNo}")
    public String findPagHidden(@PathVariable(value = "pageNo") int pageNo, Model model, Brand brand) {
        int pageSize = 10;
        Page<Brand> page = brandService.findPaginatedHidden(pageNo, pageSize);
        List<Brand> brands = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newBrand", brand);
        model.addAttribute("brands", brands);
        return "admin/brand/listBrand";
    }

    public boolean checkBrandName(String brandName, int brandID) {
        Brand brand = brandService.getBrandById(brandID);
        boolean checkBrandName = brandService.checkBrandName(brandName);
        if (checkBrandName == false) {
            if (brandName.equals(brand.getBrandName())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
