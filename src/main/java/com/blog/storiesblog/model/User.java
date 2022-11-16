package com.blog.storiesblog.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.stream.Collectors;

//@Data fixed getting data to post controller
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Username cannot be empty !")
    @Size(min= 3, max = 50, message = "Username should be 3 - 20 characters long!")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "Password cannot be empty !")
    @Size(min= 3, max = 50, message = "Password should be 3 - 20 characters long!")
    @Column(name = "password", nullable = false)
    private String password;

    @Email(message = "Incorrect email format, email should look like example@example.example")
    @Column (name = "email", nullable = false)
    private String email;

//    @OneToMany(mappedBy = "user")
//    private Collection<Post> posts;

//    @ManyToMany(cascade = CascadeType.REMOVE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Collection<Role> roles;

    public User () { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public String rolesForManagement() {
        return this.getRoles().toString().replaceAll("\\[[Role]{4}\\(", "").replaceAll("\\)\\]", "");
    }
}
