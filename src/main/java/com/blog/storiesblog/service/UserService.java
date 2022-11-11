package com.blog.storiesblog.service;

import com.blog.storiesblog.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllEUsers();

    void saveUser(User user);

    User getUserById(long id);

    void deleteUserById(long id);

}
