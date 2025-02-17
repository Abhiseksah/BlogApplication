package io.mountblue.controller;
import io.mountblue.models.Tag;
import io.mountblue.models.User;
import io.mountblue.repository.TagRepository;
import io.mountblue.repository.UserRepository;
import io.mountblue.service.PostService;
import io.mountblue.service.SortService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import io.mountblue.models.Post;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class SortingController {
    private final SortService sortService ;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public SortingController(SortService sortService,
                             UserRepository userRepository,
                             TagRepository tagRepository){
        this.sortService = sortService;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }
    @GetMapping("/posts/sort")
    public String getAllPost(@RequestParam(required = false) String sort, Model model, HttpSession session){
        List<Post> posts = (List<Post>) session.getAttribute("currentPosts");
        List<Post> sortedPosts = sortService.sortedPost(posts,sort);
        List<User> listOfUser = userRepository.findAll();
        List<Tag> listOfTag = tagRepository.findAll();
        model.addAttribute("listOfUser",listOfUser);
        model.addAttribute("posts",sortedPosts);
        model.addAttribute("tag",listOfTag);

        return "sortpost";
    }
}
