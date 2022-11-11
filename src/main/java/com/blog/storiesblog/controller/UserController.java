package com.blog.storiesblog.controller;

import com.blog.storiesblog.model.User;
import com.blog.storiesblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String homepage(Model model) {
        return "/index";
    }

    @GetMapping("/user/index")
    public String userHome(Model model) {
        return "user/index";
    }

    @GetMapping("user/userManagement")
    public String userAdmin(Model model) {
        model.addAttribute("userManagement", new User());
        model.addAttribute("listOfUsers", userService.getAllEUsers());
        return "user/userManagement";
    }
}
