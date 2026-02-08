package com.example.newAuthAndToken.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(HttpServletRequest request) {
        return "Try own auth.   ___oo___  ! " + request.getSession().getId();
    }
}
