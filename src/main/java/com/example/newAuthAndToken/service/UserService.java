package com.example.newAuthAndToken.service;

import com.example.newAuthAndToken.dto.UsersDto;
import com.example.newAuthAndToken.entity.Users;
import com.example.newAuthAndToken.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users register(UsersDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Users users = new Users();
        users.setName(dto.getName());
        users.setEmail(dto.getEmail());
        users.setPassword(passwordEncoder.encode(dto.getPassword())); // hash password

        return userRepository.save(users);
    }
}
