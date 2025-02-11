package io.mountblue.dto;

import io.mountblue.models.Post;
import io.mountblue.models.Tag;

import java.time.LocalDateTime;

public class Post_tagDto {
    private Long id;
    private Post post;
    private Tag tag;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Post_tagDto(Long id, Post post, Tag tag, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.post = post;
        this.tag = tag;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Post_tagDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
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
