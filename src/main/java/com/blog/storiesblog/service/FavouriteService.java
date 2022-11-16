package com.blog.storiesblog.service;

import com.blog.storiesblog.model.Favourite;
import com.blog.storiesblog.model.Post;
import org.springframework.security.core.userdetails.User;

public interface FavouriteService {

    void saveFavourite(Favourite favourite);

    void deleteFavourite(Favourite favourite);

    boolean isLiked(User user, Post post);


}
