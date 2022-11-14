package com.blog.storiesblog.controller;

import com.blog.storiesblog.model.User;
import com.blog.storiesblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        String encodedPass = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPass);
        userRepository.save(user);

        return "redirect:/";
    }
}
