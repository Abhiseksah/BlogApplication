package io.mountblue.controller;

import io.mountblue.dto.PostDto;
import io.mountblue.models.Comment;
import io.mountblue.models.Post;
import io.mountblue.models.Tag;
import io.mountblue.models.User;
import io.mountblue.repository.UserRepository;
import io.mountblue.service.CommentService;
import io.mountblue.service.PostService;
import io.mountblue.service.PostTagService;
import io.mountblue.service.TagService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;
    private final PostTagService postTagService;
    private final TagService tagService;
    private final CommentService commentService;
    private final UserRepository userRepository;

    public PostController(PostService postService ,
                          PostTagService postTagService,
                          TagService tagService,
                          CommentService commentService,
                          UserRepository userRepository){
        this.postService = postService;
        this.postTagService = postTagService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String dashboard(@RequestParam(value = "author" , required = false) Long authorId ,
                            @RequestParam(value = "tagId" , required = false) List<Long> tagIds ,
                            @RequestParam(value = "isPublished" , required = false) Boolean isPublished ,
                            @RequestParam(value = "startDate" , required = false) LocalDateTime startDate ,
                            @RequestParam(value = "startDate" , required = false) LocalDateTime endDate ,
                            @RequestParam(value = "sortedBy" , required = false) String sortBy ,
                            @RequestParam(value = "sortOrder" , required = false) String sortOrder ,
                            @RequestParam(value = "start" , required = false , defaultValue = "1") Integer start ,
                            @RequestParam(value = "limit" , required = false , defaultValue = "10") Integer limit ,
                            Model model,HttpSession session,
                            @AuthenticationPrincipal UserDetails userDetails){

//        int pageNo = start.intValue() ;
//        int totalItems = limit.intValue() ;
//
//        Pageable pageable = PageRequest.of(pageNo - 1 , limit) ;
//
//        Page<Post> postPage=  postService.findFilteredPosts(authorId, tagIds,  isPublished,  startDate,  endDate,  pageable,  sortBy,  sortOrder) ;
//
//        List<Post> postList = postPage.getContent() ;
//        List<Tag> tagsList = tagService.getAllTags() ;
//
//        model.addAttribute("currentPage" , pageNo) ;
//        model.addAttribute("limit" , totalItems) ;
//        model.addAttribute("totalPages" , postPage.getTotalPages()) ;
//        model.addAttribute("totalItems" , postPage.getTotalElements()) ;
//        model.addAttribute("postList" , postList) ;
//        //   model.addAttribute("postt" , new Post()) ;
//        model.addAttribute("tagList" , tagsList) ;

        //System.out.println(tagIds);


        //================================================================================
        List<Post> posts = postService.getAllPosts();
//        for(Post post : posts){
//            if(!post.getAuthor().getName().isBlank()){
//                System.out.println("author name :" + post.getAuthor().getName());
//            }else{
//                System.out.println("blank author");
//            }
//
//        }

       List<Tag> tags = tagService.getAllTags();
        if (userDetails != null) {
            // Get email from UserDetails
            String email = userDetails.getUsername();
            // Fetch full user details from database
            User user = userRepository.findByEmail(email);
            // Send user details to Thymeleaf
            model.addAttribute("user", user);
        }

        model.addAttribute("posts",posts);
       // model.addAttribute("users",user);
        model.addAttribute("tag",tags);
        return "dashboard";
    }

    @GetMapping("/post/{id}")
    public String postview(@PathVariable Long id,Model model){
        Post post = postService.findPostById(id);
        List<Comment> comment = commentService.getAllCommentByPostId(id);
        //System.out.println(id);
        model.addAttribute("post",post);
        model.addAttribute("comments",comment);
        return "post-detail";
    }

    @GetMapping ("/post/edit/{id}")
    public String showeditpost(@PathVariable Long id,Model model){
        Post post = postService.findPostById(id);
        String tag = postTagService.FindTagbyPost(id);
        //String tag = "this is autogenerated";
        model.addAttribute("post",post);
        model.addAttribute("taglist",tag);
        //System.out.println("taglist------->"+tag);
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
