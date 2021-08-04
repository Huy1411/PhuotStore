package com.example.phuotstore.controller;

import com.example.phuotstore.model.CRole;
import com.example.phuotstore.model.Customer;
import com.example.phuotstore.model.Role;
import com.example.phuotstore.model.User;
import com.example.phuotstore.service.CRoleService;
import com.example.phuotstore.service.CustomerService;
import com.example.phuotstore.service.RoleService;
import com.example.phuotstore.service.UserService;
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
@RequestMapping(path = "/admin/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CRoleService cRoleService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getCustomers(Model model) {
        Customer customer = new Customer();
        return findPaginated(1, model, customer);
    }

    @RequestMapping("/insertCustomer")
    public String insertCustomer(Model model) {
        Customer customer = new Customer();
        List<CRole> cRoles = cRoleService.getCRoles();
        model.addAttribute("newCustomer", customer);
        model.addAttribute("cRoles", cRoles);
        return "admin/customer/insertCustomer";
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("newCustomer") @Valid Customer customer, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            List<CRole> cRoles = cRoleService.getCRoles();
            model.addAttribute("newCustomer", customer);
            model.addAttribute("cRoles", cRoles);
            return "admin/customer/insertCustomer";
        }
        boolean checkCustomerName = customerService.checkCustomerName(customer.getCustomerName());
        if (checkCustomerName == false) {
            return "redirect:/admin/customer/insertCustomer?errorcustomername=Customer Name is existed";
        }
        boolean bl = customerService.saveCustomer(customer);
        if (bl) {
            return "redirect:/admin/customer/";
        }
        return "redirect:/admin/customer?error=Add New Customer error";
    }

    @RequestMapping(value = "/editCustomer")
    public String editCustomer(@RequestParam("id") Integer customerID, Model model) {
        Customer customer = customerService.getCustomerByID(customerID);
        List<CRole> cRoles = cRoleService.getCRoles();
        model.addAttribute("editCustomer", customer);
        model.addAttribute("cRoles", cRoles);
        return "admin/customer/editCustomer";
    }

    @RequestMapping(path = "/updateCustomer", method = RequestMethod.POST)
    public String updateCustomer(@ModelAttribute("editCustomer") Customer customer) {
        boolean checkCustomerName = checkCustomerName(customer.getCustomerName(), customer.getCustomerID());
        if (checkCustomerName == false) {
            return "redirect:/admin/customer/editCustomer?customerID=" + customer.getCustomerID() + "&&errorcustomername=Customer Name is existed";
        }
        boolean bl = customerService.updateCustomer(customer);
        if (bl) {
            return "redirect:/admin/customer/";
        }
        return "redirect:/admin/customer?error=Update Customer Error";
    }

    @RequestMapping(value = "/detailCustomer")
    public String detailCustomer(@RequestParam("id") Integer customerID, Model model) {
        Customer customer = customerService.getCustomerByID(customerID);
        List<CRole> cRoles = cRoleService.getCRoles();
        model.addAttribute("detailCustomer", customer);
        model.addAttribute("cRoles", cRoles);
        return "admin/customer/detailCustomer";
    }

    @RequestMapping(path = "/deleteCustomer")
    public String deleteCustomer(@RequestParam("id") Integer customerID) {
        boolean bl = customerService.deleteCustomer(customerID);
        if (bl) {
            return "redirect:/admin/customer/";
        }
        return "redirect:admin/customer?error=Delete Customer Error";
    }


    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, Customer customer) {
        int pageSize = 10;
        Page<Customer> page = customerService.findPaginated(pageNo, pageSize);
        List<Customer> customers = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newCustomer", customer);
        model.addAttribute("customers", customers);
        return "admin/customer/listCustomer";
    }


    public boolean checkCustomerName(String customerName, int customerID) {
        Customer customer = customerService.getCustomerByID(customerID);
        boolean checkCustomerName = customerService.checkCustomerName(customerName);
        if (checkCustomerName == false) {
            if (customerName.equals(customer.getCustomerName())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

}
