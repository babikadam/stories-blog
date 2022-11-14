package com.blog.storiesblog.controller;

import com.blog.storiesblog.model.Comment;
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
public class CommentController {

    private PostService postService;
    private CommentService commentService;

    @Autowired
    public CommentController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

//    @GetMapping("/posts/{id}/addComment")
//    public String addNewComment (@PathVariable(name="id") Long id, Model model){
//
//        Post post = postService.getPostById(id);
//        Comment comment = new Comment();
//        comment.setPost(post);
//
//        model.addAttribute("comment", comment);
//
//
//
//        return "/posts/newComment";
//    }
//
//    @PostMapping("/posts/{id}/saveComment")
//    public String saveComment (@PathVariable(name="id") @Valid @ModelAttribute Comment comment,
//                            BindingResult bindingResult, Post post){
//
//        if(bindingResult.hasErrors()){
//            System.out.println("test");
//            System.out.println(bindingResult.getAllErrors());
//            return "/posts/newComment";
//        }
//
//        comment.createdOn();
//        try{
//
//        commentService.saveComment(comment);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        return "redirect:/posts/";
//    }

    @GetMapping("/comment/{id}")
    public String showComment(@PathVariable Long id, Model model) {

        Post post = postService.getPostById(id);
        Comment comment = new Comment();
        comment.setPost(post);
        model.addAttribute("comment", comment);

            return "/posts/newComment";

    }

    @PostMapping("/comment/{id}")
    public String postComment(@PathVariable Long id, @Valid @ModelAttribute Comment comment,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "/posts/newComment";

        } else {
            this.commentService.saveComment(comment);

            return "redirect:/posts/";
        }
    }

    @GetMapping("/posts/editComment/{id}")
    public String editComment(@PathVariable(value="id") long id, Model model){
        Comment comment = commentService.getCommentById(id);

        model.addAttribute("comment",comment);

        return "/posts/editComment";

    }


    @GetMapping("/posts/deleteComment/{id}")
    public String deleteComment(@PathVariable(value="id") long id, Model model){
        commentService.deleteCommentById(id);

        return "redirect:/posts";

    }



}
