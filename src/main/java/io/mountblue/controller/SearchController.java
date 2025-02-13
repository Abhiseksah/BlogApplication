package io.mountblue.controller;

import io.mountblue.models.Post;
import io.mountblue.repository.PostRepository;
import io.mountblue.service.PostService;
import io.mountblue.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping("/posts/search")
    public String search(@RequestParam String keyword, Model model){
        System.out.println(keyword);
        List<Post> listOfPost = searchService.searchPost(keyword);
        model.addAttribute("listOfPost",listOfPost);
        return "searchPost";
    }
}
