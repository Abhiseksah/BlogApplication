package io.mountblue.controller;

import io.mountblue.models.Post;
import io.mountblue.repository.PostRepository;
import io.mountblue.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    private final CommentService commentService ;
    private final PostRepository postRepository;

    public CommentController(CommentService commentService , PostRepository postRepository){
        this.commentService = commentService;
        this.postRepository = postRepository;
    }

    @PostMapping("/comments/post/{id}")
    public String addComment(@RequestParam String comment, @PathVariable("id") Long id, Model model){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        commentService.saveComment(comment,id);
        model.addAttribute("comment",comment);
        return "post-detail";
    }
}
