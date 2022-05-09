package com.example.technotree.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    private String body;

    @OneToMany
    private List<Comment> comments;

    public Post() {
    }

    public Post(Long userId, String title, String body, List<Comment> comments) {
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.comments = comments;
    }

    public Post(Long id, Long userId, String title, String body, List<Comment> comments) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", comments=" + comments +
                '}';
    }
}
