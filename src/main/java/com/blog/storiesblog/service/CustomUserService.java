package com.blog.storiesblog.service;

import com.blog.storiesblog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

public interface CustomUserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    User saveUserRole(User user) throws RoleNotFoundException;

}
