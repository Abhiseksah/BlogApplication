package io.mountblue.service;

import io.mountblue.models.Comment;

import java.util.List;

public interface CommentService {
    void saveComment(String comment,Long id);
    List<Comment> getAllCommentByPostId(Long id);
}
