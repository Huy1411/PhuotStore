package com.example.phuotstore.controller;

import com.example.phuotstore.model.*;
import com.example.phuotstore.service.*;
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
@RequestMapping(path = "/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getUsers(Model model) {
        User user = new User();
        return findPaginated(1, model, user);
    }

    @RequestMapping("/insertUser")
    public String insertUser(Model model) {
        User user = new User();
        List<Role> roles = roleService.getRoles();
        model.addAttribute("newUser", user);
        model.addAttribute("roles", roles);
        return "admin/user/insertUser";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("newUser") @Valid User user, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            List<Role> roles = roleService.getRoles();
            model.addAttribute("newUser", user);
            model.addAttribute("roles", roles);
            return "admin/user/insertUser";
        }
        boolean checkUserName = userService.checkUserName(user.getUsername());
        if (checkUserName == false) {
            return "redirect:/admin/user/insertUser?errorusername=User Name is existed";
        }
        boolean bl = userService.saveUser(user);
        if (bl) {
            return "redirect:/admin/user/";
        }
        return "redirect:/admin/user?error=Add New User error";
    }

    @RequestMapping(value = "/editUser")
    public String editUser(@RequestParam("id") Integer userID, Model model) {
        User user = userService.getUserByID(userID);
        List<Role> roles = roleService.getRoles();
        model.addAttribute("editUser", user);
        model.addAttribute("roles", roles);
        return "admin/user/editUser";
    }

    @RequestMapping(path = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("editUser") User user) {
        boolean checkUserName = checkUserName(user.getUsername(), user.getUserID());
        if (checkUserName == false) {
            return "redirect:/admin/user/editUser?userID=" + user.getUserID() + "&&errorusername=User Name is existed";
        }
        boolean bl = userService.updateUser(user);
        if (bl) {
            return "redirect:/admin/user/";
        }
        return "redirect:/admin/user?error=Update User Error";
    }

    @RequestMapping(value = "/detailUser")
    public String detailUser(@RequestParam("id") Integer userID, Model model) {
        User user = userService.getUserByID(userID);
        List<Role> roles = roleService.getRoles();
        model.addAttribute("detailUser", user);
        model.addAttribute("roles", roles);
        return "admin/user/detailUser";
    }

    @RequestMapping(path = "/deleteUser")
    public String deleteUser(@RequestParam("id") Integer userID) {
        boolean bl = userService.deleteUser(userID);
        if (bl) {
            return "redirect:/admin/user/";
        }
        return "redirect:admin/user?error=Delete User Error";
    }


    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, User user) {
        int pageSize = 10;
        Page<User> page = userService.findPaginated(pageNo, pageSize);
        List<User> users = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newUser", user);
        model.addAttribute("users", users);
        return "admin/user/listUser";
    }


    public boolean checkUserName(String username, Long userID) {
        User user = userService.getUserByID(userID);
        boolean checkUserName = userService.checkUserName(username);
        if (checkUserName == false) {
            if (username.equals(user.getUsername())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

}
