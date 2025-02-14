package io.mountblue.service;

import io.mountblue.models.Comment;
import io.mountblue.models.Post;
import io.mountblue.repository.CommentRepositoy;
import io.mountblue.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepositoy commentRepositoy;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepositoy commentRepositoy, PostRepository postRepository){
        this.commentRepositoy = commentRepositoy;
        this.postRepository = postRepository;
    }
    @Override
    public void saveComment(String comment, Long id) {
        Comment comm = new Comment();
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        comm.setPost(post);
        comm.setComment(comment);
        comm.setCreated_at(LocalDateTime.now());
        Comment savedComment = commentRepositoy.save(comm);
        post.getComments().add(savedComment);
        postRepository.save(post);
    }

    @Override
    public List<Comment> getAllCommentByPostId(Long id) {
        List<Comment> comments = commentRepositoy.findBypost_id(id);
        return comments;
    }
}
