package io.mountblue.controller;

import io.mountblue.models.Post;
import io.mountblue.models.Tag;
import io.mountblue.models.User;
import io.mountblue.repository.PostRepository;
import io.mountblue.repository.UserRepository;
import io.mountblue.service.PostService;
import io.mountblue.service.SearchService;
import io.mountblue.service.TagService;
import org.apache.tomcat.util.buf.UEncoder;
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
    UserRepository userRepository;

    public SearchController(SearchService searchService, TagService tagService, UserRepository userRepository){
        this.searchService = searchService;
        this.tagService = tagService;
        this.userRepository = userRepository;
    }

    @GetMapping("/posts/search")
    public String search(@RequestParam String keyword, Model model){
        List<Post> listOfPost = searchService.searchPost(keyword);
        List<Tag> tags = tagService.getAllTags();
        List<User> user = userRepository.findAll();
        model.addAttribute("tag",tags);
        model.addAttribute("listOfPost",listOfPost);
        model.addAttribute("user",user);
        return "searchPost";
    }
}
