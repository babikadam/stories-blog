package com.blog.storiesblog.controller;

import com.blog.storiesblog.model.Post;
import com.blog.storiesblog.service.CommentService;
import com.blog.storiesblog.service.CustomUserServiceImpl;
import com.blog.storiesblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PostController {

    PostService postService;
    CommentService commentService;
    CustomUserServiceImpl userService;

    @Autowired
    public PostController(PostService postService,
                          CommentService commentService,
                          CustomUserServiceImpl userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/posts")
    public String postHome (Model model){
        model.addAttribute("listOfPosts", postService.getAllPosts());
        model.addAttribute("listOfComments", commentService.getAllComments());
        return "/posts/index";

    }

    @GetMapping("/posts/addNewPost")
//    @PreAuthorize("hasAnyRole('ADMIN_ROLE', 'USER_ROLE')")
    public String addNewPost (Model model,
                              Principal principal){
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        model.addAttribute("post", new Post());
        model.addAttribute("authUsername", authUsername);

        return "/posts/newPost";
    }

    @PostMapping("/posts/savePost")
//    @PreAuthorize("userService.hasAnyRole('ADMIN_ROLE', 'USER_ROLE')")
    public String savePost (@Valid @ModelAttribute Post post,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/posts/newPost";
        }
        post.createdOn();
        postService.savePost(post);

        return "redirect:/posts/";
    }

    @GetMapping("/posts/editPost/{id}")
//    @PreAuthorize("hasRole('ADMIN_ROLE')")
    public String editPost (@PathVariable(value="id") long id, Model model, Principal principal){
        String authUsername = principal.getName();
        Post post = postService.getPostById(id);

        model.addAttribute("post",post);
        model.addAttribute("authUsername",authUsername);
        return "/posts/editPost";

    }


    @GetMapping("/posts/deletePost/{id}")
//    @PreAuthorize("hasRole('ADMIN_ROLE')")
    public String deletePost (@PathVariable(value="id") long id, Model model){
        postService.deletePostById(id);

        return "redirect:/posts";

    }

}
