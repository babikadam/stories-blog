package com.blog.storiesblog.service;

import com.blog.storiesblog.model.Favourite;
import com.blog.storiesblog.model.Post;
import com.blog.storiesblog.repository.FavouriteRepository;
import com.blog.storiesblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class FavouriteServiceImpl implements FavouriteService{


    private FavouriteRepository favRepository;
    private UserRepository userRepository;

    @Autowired
    public FavouriteServiceImpl(FavouriteRepository favRepository, UserRepository userRepository) {
        this.favRepository = favRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void saveFavourite(Favourite favourite) {
      this.favRepository.save(favourite);
    }


    @Override
    public void deleteFavourite(Favourite favourite) {
        this.favRepository.delete(favourite);
    }

    @Override
    public boolean isLiked(User user, Post post) {
        return false;
    }
}
