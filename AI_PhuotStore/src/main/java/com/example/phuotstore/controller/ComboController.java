package com.example.phuotstore.controller;

import com.example.phuotstore.model.Combo;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.service.ComboService;
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
@RequestMapping(path = "/admin/combo")
public class ComboController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ComboService comboService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getCombos(Model model) {
        Combo combo = new Combo();
        return findPaginated(1, model, combo);
    }

    @RequestMapping("show")
    public String getCombosShow(Model model) {
        Combo combo = new Combo();
        return findPagShow(1, model, combo);
    }

    @RequestMapping("hidden")
    public String getCombosHidden(Model model) {
        Combo combo = new Combo();
        return findPagHidden(1, model, combo);
    }

    @RequestMapping("/insertCombo")
    public String insertCombo(Model model) {

        Combo combo = new Combo();
        List<Product> products = productService.getProductsByStatus(1);
        model.addAttribute("newCombo", combo);
        model.addAttribute("products", products);
        return "admin/combo/insertCombo";
    }

    @RequestMapping(value = "/saveCombo", method = RequestMethod.POST)
    public String saveCombo(@ModelAttribute("newCombo") @Valid Combo combo, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            List<Product> products = productService.getAllProducts();
            model.addAttribute("newCombo", combo);
            model.addAttribute("products", products);
            return "admin/combo/insertCombo";
        }
        boolean checkComboName = comboService.checkComboName(combo.getComboName());
        if (checkComboName == false) {
            return "redirect:/admin/combo/insertCombo?errorcomboname=Combo Name is existed";
        }

        boolean bl = comboService.saveCombo(combo);
        if (bl) {
            return "redirect:/admin/combo/";
        }
        return "redirect:/admin/combo?error=Add New Combo error";
    }

    @RequestMapping(value = "/editCombo")
    public String editCombo(@RequestParam("id") Integer comboID, Model model) {
        Combo combo = comboService.getComboByID(comboID);
        List<Product> products = productService.getProductsByStatus(1);
        model.addAttribute("editCombo", combo);
        model.addAttribute("products", products);
        return "admin/combo/editCombo";
    }

    @RequestMapping(path = "/updateCombo", method = RequestMethod.POST)
    public String updateCombo(@ModelAttribute("editCombo") Combo combo) {
        boolean checkComboName = checkComboName(combo.getComboName(), combo.getComboID());
        if (checkComboName == false) {
            return "redirect:/admin/combo/editCombo?comboID=" + combo.getComboID() + "&&errorcomboname=Combo Name is existed";
        }
        boolean bl = comboService.updateCombo(combo);
        if (bl) {
            return "redirect:/admin/combo/";
        }
        return "redirect:/admin/combo?error=Update Combo Error";
    }

    @RequestMapping(value = "/detailCombo")
    public String detailComboById(@RequestParam("id") Integer comboID, Model model) {
        Combo combo = comboService.getComboByID(comboID);
        model.addAttribute("detailCombo", combo);
        return "admin/combo/detailCombo";
    }

    @RequestMapping(path = "/deleteCombo")
    public String deleteCombo(@RequestParam("id") Integer comboID) {
        boolean bl = comboService.deleteCombo(comboID);
        if (bl) {
            return "redirect:/admin/combo/";
        }
        return "redirect:admin/combo?error=Delete Combo Error";
    }


    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, Combo combo) {
        int pageSize = 10;
        Page<Combo> page = comboService.findPaginated(pageNo, pageSize);
        List<Combo> combos = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newCombo", combo);
        model.addAttribute("combos", combos);
        return "admin/combo/listCombo";
    }

    @RequestMapping("/pageShow/{pageNo}")
    public String findPagShow(@PathVariable(value = "pageNo") int pageNo, Model model, Combo combo) {
        int pageSize = 10;
        Page<Combo> page = comboService.findPaginatedShow(pageNo, pageSize);
        List<Combo> combos = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newCombo", combo);
        model.addAttribute("combos", combos);
        return "admin/combo/listCombo";
    }

    @RequestMapping("/pageHidden/{pageNo}")
    public String findPagHidden(@PathVariable(value = "pageNo") int pageNo, Model model, Combo combo) {
        int pageSize = 10;
        Page<Combo> page = comboService.findPaginatedHidden(pageNo, pageSize);
        List<Combo> combos = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newCombo", combo);
        model.addAttribute("combos", combos);
        return "admin/combo/listCombo";
    }

    public boolean checkComboName(String comboName, int comboID) {
        Combo combo = comboService.getComboByID(comboID);
        boolean checkComboName = comboService.checkComboName(comboName);
        if (checkComboName == false) {
            if (comboName.equals(combo.getComboName())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
