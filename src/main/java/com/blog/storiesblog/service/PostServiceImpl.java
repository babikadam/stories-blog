package com.blog.storiesblog.service;

import com.blog.storiesblog.model.Post;
import com.blog.storiesblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<Post> getAllPosts() {

        return postRepository.findAll(Sort.by(Sort.Direction.DESC,"postDate"));
    }

    @Override
    public void savePost(Post post) {
        this.postRepository.save(post);

    }

    @Override
    public Post getPostById(long id) {
        Optional<Post> optional = this.postRepository.findById(id);
        Post post = null;

        if(optional.isPresent()){
            post = optional.get();
        } else {
            throw new RuntimeException("Post with that id" + id + "does not exist !");
        }
        return  post;

    }

    @Override
    public void deletePostById(long id) {
        boolean exists = this.postRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Post with id " + id + " does not exist !");
        }
        this.postRepository.deleteById(id);
    }
}
