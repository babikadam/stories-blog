package com.blog.storiesblog.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name ="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "post cannot be empty !")
    @Size(max = 2000, message = "Maximum of 2000 chars is allowed !")
    @Column(name = "comment")
    private String commentContent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="commentdate", updatable = false)
    private Date commentDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="updatedate")
    private Date updateDate;

    @PrePersist
    public void createdOn(){
        commentDate = new Date();
        updateDate = new Date();
    }

    @NotEmpty(message = "User cannot be empty !")
    @Column(name = "author")
    private String author;


    @NotNull
    @ManyToOne (fetch =FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;


    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
