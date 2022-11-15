package com.blog.storiesblog.repository;

import com.blog.storiesblog.model.Role;
import com.blog.storiesblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findRoleByName(String name);
}
