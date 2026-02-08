package com.example.newAuthAndToken.controller;

import com.example.newAuthAndToken.dto.UsersDto;
import com.example.newAuthAndToken.entity.Users;
import com.example.newAuthAndToken.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsersDto dto) {
        Users users = userService.register(dto);
        return ResponseEntity.ok(users);
    }
}
