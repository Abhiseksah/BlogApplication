package io.mountblue.controller;

import io.mountblue.dto.UserDto;
import io.mountblue.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/signup")
    public String showRegisterPage(Model model) {
        System.out.println("inside get signup");
        model.addAttribute("userDto",new UserDto());
        return "register";
    }

    @PostMapping("/signup")
    public String processRegister(@Valid @ModelAttribute UserDto userDto,
                                  BindingResult result,
                                  Model model) {

        userDto.setRole("ROLE_AUTHOR");
        System.out.println("Email--->"+userDto.getEmail());
        String message = userService.addUser(userDto);

        if (!message.equals("User registered successfully")) {
            model.addAttribute("error", message);
            return "register";
        }
        return "redirect:/login";
    }
}
