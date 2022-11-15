package com.blog.storiesblog.controller;

import com.blog.storiesblog.model.Role;
import com.blog.storiesblog.model.User;
import com.blog.storiesblog.repository.UserRepository;
import com.blog.storiesblog.service.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private CustomUserServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Role role)
            throws RoleNotFoundException {
            System.err.println("New user: "+ user); //testing
        //checking unique username
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "error.username",
                    "This username is already taken");
        }
        if (bindingResult.hasErrors()) {
            System.err.println("New user not validated"); // for our use only
            return "register";
        }



        //saving user and role for new user
        this.userDetailsService.saveUserAndRole(user);

        return "redirect:/";
    }




}
