package io.mountblue.controller;

import io.mountblue.models.Post;
import io.mountblue.models.Tag;
import io.mountblue.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilterController {

    PostRepository postRepository;
    public FilterController(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @GetMapping("/posts/filter")
    public String showPost(@RequestParam(required = false) List<Post> posts,
                           @RequestParam(required = false) List<Tag> tags,
                           @RequestParam(required = false) String date,
                           Model model){
        List<Post> listOfPost = postRepository.findAll();
        model.addAttribute("listOfPost",listOfPost);

        return "filterpost";
    }
}
