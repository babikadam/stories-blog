package com.blog.storiesblog.service;

import com.blog.storiesblog.model.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    void savePost (Post post);

    Post getPostById (long id);

    void deletePostById (long id);

}
