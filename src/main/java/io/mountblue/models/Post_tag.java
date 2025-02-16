package io.mountblue.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post_tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Post_tag(Post post, Tag tag, LocalDateTime created_at, LocalDateTime updated_at) {
        this.post = post;
        this.tag = tag;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Post_tag() {
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
