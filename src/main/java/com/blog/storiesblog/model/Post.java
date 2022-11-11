package com.blog.storiesblog.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Title cannot be empty !")
    @NotNull
    @Size(min= 1, max = 50, message = "Maximum size of title is 50 characters !")
    @Column(name = "post_title")
    private String postTitle;

    @NotEmpty(message = "Enter a date of a post")
    @NotNull
    @Column(name = "post_date")
    private String postDate;

    @NotEmpty
    @NotNull
    @Column( name = "author")
    private String author;

    @NotEmpty(message = " Your post cant be empty !")
    @NotNull
    @Size(min = 1, max = 2000, message = "Your post size can be 2000 chars long !")
    @Column(name = "content")
    private String content;

//    @OneToMany (mappedBy = "comments");
//    private List<Comment> comments = new ArrayList<>();
//

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
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
