package com.blog.storiesblog.service;

import com.blog.storiesblog.model.User;
import com.blog.storiesblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAllEUsers() {
        //SELECT * FROM user; return all rows from user table

        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        //INSERT INTO user (col1, col2, col3, ...) VALUES ('val1', 'val2', 'val3', ...);
        this.userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        //SELECT * FROM employee WHERE id = id
        Optional<User> optional = this.userRepository.findById(id);
        User user = null;

        if(optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User with ID: " + id + " does not exist");
        }
        return user;
    }

    @Override
    public void deleteUserById(long id) {
        //DELETE FROM user WHERE id = id;
        this.userRepository.deleteById(id);
    }
}
