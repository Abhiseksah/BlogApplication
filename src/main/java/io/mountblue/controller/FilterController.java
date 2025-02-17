package io.mountblue.controller;

import io.mountblue.models.Post;
import io.mountblue.models.Tag;
import io.mountblue.repository.PostRepository;
import io.mountblue.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilterController {

    PostRepository postRepository;
    SearchService searchService;
    public FilterController(PostRepository postRepository,SearchService searchService){
        this.postRepository = postRepository;
        this.searchService = searchService;
    }
    @PostMapping("/posts/filter")
    public String showPost(@RequestParam(required = false) List<Post> posts,
                           @RequestParam(required = false) List<Tag> tags,
                           @RequestParam(required = false) String date,
                           @PathVariable("tag") String tag,
                           @PathVariable("author") String author,
                           Model model){
        List<Post> listOfPost = searchService.searchPost(tag);
        //List<Post> listOfPost = postRepository.findAll();
        System.out.println("tag-->"+tag);
        System.out.println("Author-->"+author);
        model.addAttribute("listOfPost",listOfPost);

        return "filterpost";
    }
}
