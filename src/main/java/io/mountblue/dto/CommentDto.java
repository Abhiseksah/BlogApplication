package io.mountblue.dto;

import io.mountblue.models.Post;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String comment;
    private Post post;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public CommentDto(Long id, String name, String email, String comment, Post post, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.post = post;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public CommentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
