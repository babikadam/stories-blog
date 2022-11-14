package com.blog.storiesblog.controller;

import com.blog.storiesblog.model.Post;
import com.blog.storiesblog.service.CommentService;
import com.blog.storiesblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PostController {

    PostService postService;
    CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/posts")
    public String postHome (Model model){
        model.addAttribute("listOfPosts", postService.getAllPosts());
        model.addAttribute("listOfComments", commentService.getAllComments());
        return "/posts/index";

    }

    @GetMapping("/posts/addNewPost")
    public String addNewPost (Model model){

        model.addAttribute("post", new Post());
        return "/posts/newPost";
    }

    @PostMapping("/posts/savePost")
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
    public String editPost (@PathVariable(value="id") long id, Model model){
        Post post = postService.getPostById(id);

        model.addAttribute("post",post);

        return "/posts/editPost";

    }


    @GetMapping("/posts/deletePost/{id}")
    public String deletePost (@PathVariable(value="id") long id, Model model){
        postService.deletePostById(id);

        return "redirect:/posts";

    }


}
