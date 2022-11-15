package com.blog.storiesblog.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//static annotation processing, so I can access user-related methods in post controller
@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Title cannot be empty !")
    @Size(min= 1, max = 50, message = "Maximum size of title is 50 characters !")
    @Column(name = "posttitle")
    private String postTitle;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "postdate", nullable = false, updatable = false)
    private Date postDate;

    @PrePersist
    public void createdOn(){
        postDate = new Date();
        updateDate = new Date();
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedate")
    private Date updateDate;


    @NotEmpty(message = "User cannot be empty")
    @Column( name = "author")
    private String author;

    @NotEmpty(message = " Your post cant be empty !")
    @Size(min = 1, max = 2000, message = "Your post size can be 2000 chars long !")
    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

//   @NotNull
//   @ManyToOne
//   @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//   private User user;
////    @NotEmpty(message = "User cannot be empty")
//////    @JoinColumn( name = "author", referencedColumnName = "username", nullable = false)


    public Post() {

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    //debugging new post
//    @Override
//    public String toString() {
//        return "id=" + id + ", postTitle='" + postTitle + ", content='" + content + '\'' +
//                ", username=" + user.getUsername() +
//                ", user=" + user.getId();
//    }
}
