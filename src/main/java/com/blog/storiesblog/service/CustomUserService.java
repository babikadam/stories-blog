package com.blog.storiesblog.service;

import com.blog.storiesblog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.management.relation.RoleNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CustomUserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    User saveUserAndRole(User user) throws RoleNotFoundException;

    List<User> getAllUsers();

    void deleteUserById (long id);

    User getUserById (long id);

    void saveUser (User user);

}
