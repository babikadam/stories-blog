package com.blog.storiesblog.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Title cannot be empty !")
    @Size(min= 1, max = 50, message = "Maximum size of title is 50 characters !")
    @Column(name = "post_title")
    private String postTitle;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_date")
    private Date postDate;

    @PrePersist
    public void createdOn(){
        postDate = new Date();
    }


    @NotEmpty(message = "User cannot be empty")
    @Column( name = "author")
    private String author;

    @NotEmpty(message = " Your post cant be empty !")
    @Size(min = 1, max = 2000, message = "Your post size can be 2000 chars long !")
    @Column(name = "content")
    private String content;

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




}
