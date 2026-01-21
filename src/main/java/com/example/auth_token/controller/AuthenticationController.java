package com.example.auth_token.controller;

import com.example.auth_token.entity.UserEntity;
import com.example.auth_token.service.CustomUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("register")
public class AuthenticationController {

    private final CustomUserDetailsService userDetailsService;

    public AuthenticationController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String register(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping
    public String postUser(@ModelAttribute("user") UserEntity user,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        // Save the user to database
        // Send the confirmation email
        userDetailsService.registerUser(user);
        redirectAttributes.addFlashAttribute(
                "message",
                "Please Confirm your email address.");
        return "redirect:/";
    }

    @GetMapping("/confirmToken")
    public String confirmToken(@RequestParam("token") String token, Model model) {
        userDetailsService.confirmToken(token);
        return "confirmToken";
    }
}
