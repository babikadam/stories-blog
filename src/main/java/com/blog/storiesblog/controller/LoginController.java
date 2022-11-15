package com.blog.storiesblog.controller;

import com.blog.storiesblog.model.Post;
import com.blog.storiesblog.model.User;
import com.blog.storiesblog.service.CommentService;
import com.blog.storiesblog.service.CustomUserServiceImpl;
import com.blog.storiesblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;

@Controller
public class LoginController {

    PostService postService;

    CommentService commentService;

    CustomUserServiceImpl userService;

    @Autowired
    public LoginController(PostService postService, CommentService commentService, CustomUserServiceImpl userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/userManagement")
    public String userManagement (Model model){
        model.addAttribute("listOfUsers", userService.getAllUsers());
        return "/user/userManagement";
    }

    @GetMapping("/user/deleteUser/{id}")
    public String deleteUser (@PathVariable(value="id") long id, Model model){
        userService.deleteUserById(id);

        return "redirect:/user/userManagement";

    }


    @GetMapping("/user/editUser/{id}")
//    @PreAuthorize("hasRole('ADMIN_ROLE')")
    public String editPost (@PathVariable(value="id") long id, Model model){

        User user = userService.getUserById(id);

        model.addAttribute("user",user);

        return "/user/userEdit";

    }


    @PostMapping("/user/saveUser")
//    @PreAuthorize("userService.hasAnyRole('ADMIN_ROLE', 'USER_ROLE')")
    public String saveUser (@Valid @ModelAttribute User user,
                            BindingResult bindingResult) throws RoleNotFoundException {
        if(bindingResult.hasErrors()){
            return "/user/userEdit";
        }

        userService.saveUser(user);

        return "redirect:/user/userManagement";
    }

}
