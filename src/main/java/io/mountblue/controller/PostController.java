package io.mountblue.controller;

import io.mountblue.models.Post;
import io.mountblue.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String dashboard(Model model){
        model.addAttribute("message","This is Dashboard");
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        //System.out.println("All posts-->"+posts);
        return "dashboard";
    }

}
