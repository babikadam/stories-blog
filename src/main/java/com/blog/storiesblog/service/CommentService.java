package com.blog.storiesblog.service;

import com.blog.storiesblog.model.Comment;
import com.blog.storiesblog.model.Post;

import java.util.List;

public interface CommentService {

        List<Comment> getAllComments();

        void saveComment (Comment comment);

        Comment getCommentById (long id);

        void deleteCommentById (long id);


}
