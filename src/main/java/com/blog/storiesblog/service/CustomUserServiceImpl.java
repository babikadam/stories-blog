package com.blog.storiesblog.service;

import com.blog.storiesblog.model.Comment;
import com.blog.storiesblog.model.Role;
import com.blog.storiesblog.model.User;
import com.blog.storiesblog.repository.RoleRepository;
import com.blog.storiesblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserServiceImpl implements CustomUserService {
    //role for every registered user
    // "USER_ROLE" = 2 , ADMIN_ROLE = 1

    private final Long DEFAULT_ROLE = 2L;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public CustomUserServiceImpl (UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);


        if(!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
            User user = optionalUser.get();

            List<GrantedAuthority> grantedAuthorities = user
                    .getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority((role.getRole())))
                    .collect(Collectors.toList());
        System.err.printf(user.getUsername() +"\n" +  user.getPassword() +"\n" + grantedAuthorities);
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                          user.getPassword(), grantedAuthorities);


    }


    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUserAndRole(User user) throws RoleNotFoundException {
        Optional<Role> optionalRole = this.roleRepository.findRoleById(DEFAULT_ROLE);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            Collection<Role> roles = Collections.singletonList(role);
            user.setRoles(roles);

            //encoding pass for new user
            String encodedPass = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encodedPass);

            return this.userRepository.saveAndFlush(user);
        } else {
            throw new RoleNotFoundException("Default role not found for blog user with username " + user.getUsername());
        }
    }

    @Override
    public boolean isAllowed(String username, Principal principal) {
        return false;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (role.equals("ADMIN")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(long id) {
        boolean exists = this.userRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Comment with id: " + id + " does not exist !");
        }
        this.userRepository.deleteById(id);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> optional = this.userRepository.findById(id);
        User user = null;

        if(optional.isPresent()){
            user = optional.get();
        } else {
            throw new RuntimeException("User with that id: " + id + " does not exist !");
        }
        return  user;

    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);

    }


}
