package io.mountblue.controller;

import io.mountblue.dto.LoginDto;
import io.mountblue.dto.UserDto;
import io.mountblue.models.Post;
import io.mountblue.models.Tag;
import io.mountblue.models.User;
import io.mountblue.repository.UserRepository;
import io.mountblue.service.LoginService;
import io.mountblue.service.PostService;
import io.mountblue.service.TagService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {
    private final LoginService loginService;
    private final PostService postService;
    private final TagService tagService;
    private final UserRepository userRepository;

    public LoginController(LoginService loginService, PostService postService, TagService tagService,UserRepository userRepository){
        this.loginService = loginService;
        this.postService = postService;
        this.tagService = tagService;
        this.userRepository = userRepository;
    }
    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute LoginDto loginDto,
//                        BindingResult result,
//                        Model model,
//                        HttpSession session){
//        String message = loginService.verifyUser(loginDto);
//        List<Post> posts = postService.getAllPosts();
//        List<Tag> tags = tagService.getAllTags();
//
//        if(!message.equals("LoggedIn successfully")){
//            model.addAttribute("error", message);
//            return "login";
//        }
//        User user = userRepository.findByEmail(loginDto.getEmail());
//        session.setAttribute("user",user);
////        model.addAttribute("posts",posts);
////        model.addAttribute("tag",tags);
//        return "redirect:/";
//    }
}
