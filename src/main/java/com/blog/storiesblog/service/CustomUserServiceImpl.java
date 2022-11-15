package com.blog.storiesblog.service;

import com.blog.storiesblog.model.Role;
import com.blog.storiesblog.model.User;
import com.blog.storiesblog.repository.RoleRepository;
import com.blog.storiesblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    private final String DEFAULT_ROLE = "USER_ROLE";
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public CustomUserServiceImpl (UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return user.get();
        }

        //User user = optionalUser.get();

//        List<GrantedAuthority> grantedAuthorities = user.getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());


    }


    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUserRole(User user) throws RoleNotFoundException {
        Optional<Role> optionalRole = this.roleRepository.findRoleByName(DEFAULT_ROLE);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            Collection<Role> roles = Collections.singletonList(role);
            user.setRoles(roles);
            return this.userRepository.saveAndFlush(user);
        } else {
            throw new RoleNotFoundException("Default role not found for blog user with username " + user.getUsername());
        }
    }
}
