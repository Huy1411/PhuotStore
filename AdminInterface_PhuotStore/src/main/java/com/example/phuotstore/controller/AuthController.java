package com.example.phuotstore.controller;


import com.example.phuotstore.model.User;
import com.example.phuotstore.service.SecurityService;
import com.example.phuotstore.service.UserService;
import com.example.phuotstore.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("userForm",new User());
        return "admin/auth/register";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult)
    {
        userValidator.validate(userForm,bindingResult);
        if (bindingResult.hasErrors())
        {
            return "admin/auth/register";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:admin/brand/listBrand";
    }


    @RequestMapping(path = "/login")
    public String login(Model model, String error, String logout)
    {
        if (error != null)
        {
            model.addAttribute("error","You username and password is invalid.");
        }
        if (logout != null)
        {
            model.addAttribute("message","You have been logged out successfully.");
        }
        return "admin/auth/login";
    }

    @GetMapping({"/","/home"})
    public String home(Model model) {
        return "admin/home";
    }

}
