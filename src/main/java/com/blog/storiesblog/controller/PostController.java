package com.blog.storiesblog.controller;

import com.blog.storiesblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String postHome (Model model){
        model.addAttribute("listOfPosts", postService.getAllPosts());
        return "/posts/index";

    }

}
