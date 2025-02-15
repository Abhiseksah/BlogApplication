package io.mountblue.controller;
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

    public SortingController(SortService sortService){
        this.sortService = sortService;
    }
    @GetMapping("/posts/sort")
    public String getAllPost(@RequestParam(required = false) String sort, Model model, HttpSession session){
        List<Post> posts = (List<Post>) session.getAttribute("currentPosts");
        System.out.println("-->"+sort);
        List<Post> sortedPosts = sortService.sortedPost(posts,sort);

        System.out.println(sortedPosts);
        //List<Post> sortedList = postService.
        model.addAttribute("posts",sortedPosts);

        return "sortpost";
    }
}
