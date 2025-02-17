package io.mountblue.controller;

import io.mountblue.dto.PostDto;
import io.mountblue.models.Comment;
import io.mountblue.models.Post;
import io.mountblue.models.Tag;
import io.mountblue.models.User;
import io.mountblue.repository.Post_TagRepository;
import io.mountblue.repository.UserRepository;
import io.mountblue.service.CommentService;
import io.mountblue.service.PostService;
import io.mountblue.service.PostTagService;
import io.mountblue.service.TagService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private final PostTagService postTagService;
    private final TagService tagService;
    private final CommentService commentService;
    private final UserRepository userRepository;
    private final Post_TagRepository postTagRepository;
    private final SearchController searchController;

    public PostController(PostService postService ,
                          PostTagService postTagService,
                          TagService tagService,
                          CommentService commentService,
                          UserRepository userRepository,
                          Post_TagRepository postTagRepository,
                          SearchController searchController){
        this.postService = postService;
        this.postTagService = postTagService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.userRepository = userRepository;
        this.postTagRepository = postTagRepository;
        this.searchController = searchController;
    }

    @GetMapping("/")
    public String dashboard(@RequestParam(value = "author" , required = false) String author ,
                            @RequestParam(value = "tag" , required = false) String tag,
                            @RequestParam(value = "isPublished" , required = false) Boolean isPublished ,
                            @RequestParam(value = "startDate" , required = false) LocalDateTime startDate ,
                            @RequestParam(value = "startDate" , required = false) LocalDateTime endDate ,
                            @RequestParam(value = "sortedBy" , required = false) String sortBy ,
                            @RequestParam(value = "sortOrder" , required = false) String sortOrder ,
                            @RequestParam(value = "start" , required = false , defaultValue = "1") Integer start ,
                            @RequestParam(value = "limit" , required = false , defaultValue = "10") Integer limit ,
                            Model model,HttpSession session,
                            @AuthenticationPrincipal UserDetails userDetails){

        List<Post> posts = postService.getAllPosts();
        if(tag!=null){
            posts = postService.searchPost(tag);
        }
        if(author!=null){
            List<Post> list=postService.searchPost(author);
            for(Post l:list){
                posts.add(l);
            }
        }
        session.setAttribute("currentPosts" , posts);
        List<Tag> tags = tagService.getAllTags();
        if (userDetails != null) {
            String email = userDetails.getUsername();
        }
        List<User> user = userRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("posts",posts);
        model.addAttribute("tag",tags);
        return "dashboard";
    }

    @GetMapping("/post/{id}")
    public String postview(@PathVariable Long id,Model model){
        Post post = postService.findPostById(id);
        List<Comment> comment = commentService.getAllCommentByPostId(id);
        List<String> tags = postTagRepository.findTagsByPostId(id);
        model.addAttribute("post",post);
        model.addAttribute("comments",comment);
        model.addAttribute("tags",tags);

        return "post-detail";
    }

    @GetMapping ("/post/edit/{id}")
    public String showeditpost(@PathVariable Long id,Model model){
        Post post = postService.findPostById(id);
        String tag = postTagService.FindTagbyPost(id);
        model.addAttribute("post",post);
        model.addAttribute("taglist",tag);
        return "editpost";
    }
    @PostMapping("/post/edit/{id}")
    public String editpost(@ModelAttribute PostDto postDto){
        postService.updatePost(postDto);
        return "redirect:/";
    }
    @PostMapping("/post/delete/{id}")
    public String deletepost(@PathVariable Long id){
        postService.deletePost(id);
        return "redirect:/";
    }


    @GetMapping("/newpost")
    public String showNewPostFrom(Model model){
        Post post = new Post();
        model.addAttribute("post",post);
        model.addAttribute("tag",new String());
        return "newpost";
    }
    @PostMapping("/newpost")
    public String newpost(@ModelAttribute PostDto post,@RequestParam String tag,@AuthenticationPrincipal UserDetails userDetails){
        if (userDetails != null) {
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            post.setAuthor(user);
            System.out.println("UserDetails-->"+user.getName());
        }else{
            System.out.println("UserDetails-->is nUll");
        }

        postService.savePost(post,tag);
        return "redirect:/";
    }

}
