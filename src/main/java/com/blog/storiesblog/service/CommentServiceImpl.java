package com.blog.storiesblog.service;

import com.blog.storiesblog.model.Comment;
import com.blog.storiesblog.model.Post;
import com.blog.storiesblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements  CommentService{

    private CommentRepository commentRepository;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }


    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll(Sort.by(Sort.Direction.ASC,"commentDate"));
    }

    @Override
    public void saveComment(Comment comment) {
        this.commentRepository.save(comment);

    }

    @Override
    public Comment getCommentById(long id) {
        Optional<Comment> optional = this.commentRepository.findById(id);
        Comment comment = null;

        if(optional.isPresent()){
            comment = optional.get();
        } else {
            throw new RuntimeException("Comment with that id: " + id + " does not exist !");
        }
        return  comment;

    }

    @Override
    public void deleteCommentById(long id) {
        boolean exists = this.commentRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Comment with id: " + id + " does not exist !");
        }
        this.commentRepository.deleteById(id);
    }
}
