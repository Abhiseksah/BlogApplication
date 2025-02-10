package io.mountblue.controller;

import io.mountblue.models.Post;
import io.mountblue.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        return "dashboard";
    }

//    @PostMapping("/newpost")
//    public String newpost(@RequestBody )
    @GetMapping("/post/{id}")
    public String postview(@PathVariable Long id,Model model){
        Post post = postRepository.findById(id).orElse(null);
        if(post== null){
            System.out.println("post is null");
        }else{
            model.addAttribute("post",post);
        }

        return "post-detail";
    }
}
