package io.mountblue.controller;

import io.mountblue.models.Post;
import io.mountblue.models.Tag;
import io.mountblue.repository.PostRepository;
import io.mountblue.service.PostService;
import io.mountblue.service.SearchService;
import io.mountblue.service.TagService;
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
    TagService tagService;

    public SearchController(SearchService searchService,TagService tagService){
        this.searchService = searchService;
        this.tagService = tagService;
    }

    @GetMapping("/posts/search")
    public String search(@RequestParam String keyword, Model model){
        List<Post> listOfPost = searchService.searchPost(keyword);
        List<Tag> tags = tagService.getAllTags();
        model.addAttribute("tag",tags);
        model.addAttribute("listOfPost",listOfPost);
        return "searchPost";
    }
}
