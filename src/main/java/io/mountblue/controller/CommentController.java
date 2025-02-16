package io.mountblue.controller;

import io.mountblue.models.Comment;
import io.mountblue.models.Post;
import io.mountblue.repository.CommentRepositoy;
import io.mountblue.repository.PostRepository;
import io.mountblue.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService ;
    private final PostRepository postRepository;
    private final CommentRepositoy commentRepositoy;

    public CommentController(CommentService commentService ,
                             PostRepository postRepository,
                             CommentRepositoy commentRepositoy){
        this.commentService = commentService;
        this.postRepository = postRepository;
        this.commentRepositoy =commentRepositoy;
    }

    @PostMapping("/comments/post/{id}")
    public String addComment(@RequestParam String comment, @PathVariable("id") Long id, Model model){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("post not found with id: " + id));
        commentService.saveComment(comment,id);
        model.addAttribute("post",post);
        return "post-detail";
    }

    @PostMapping("/comments/reply/{id}")
    public String replyOnComment(@RequestParam String reply,@PathVariable("id") Long id,Model model){
        Comment comment = commentRepositoy.findById(id).orElseThrow(() -> new RuntimeException("comment not found with id: " + id));
        Comment comm = new Comment();
        comm.setComment(reply);
        comm = commentRepositoy.save(comm);
        comment.getComments().add(comm);
        commentRepositoy.save(comment);
        return "redirect:/";
    }
}
