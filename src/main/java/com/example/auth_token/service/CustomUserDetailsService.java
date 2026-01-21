package com.example.auth_token.service;

import com.example.auth_token.entity.TokenEntity;
import com.example.auth_token.entity.UserEntity;
import com.example.auth_token.repository.TokenRepository;
import com.example.auth_token.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public CustomUserDetailsService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void registerUser(UserEntity user) {
        // Check the database this email or userName exist or not
        userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
                .ifPresent(
                        existingUser -> {
                            throw new IllegalStateException("User already edists");
                        }
                );
        // If user dose not edist.
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);

        // Verify Email

        TokenEntity ConfirmationToken = new TokenEntity(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        tokenService.save(ConfirmationToken);
    }

    public void confirmToken(String token) {
        // Check token exist
        TokenEntity confirmedToken = tokenService.findByToken(token)
                .orElseThrow(
                        () -> new IllegalStateException("Invalid token")
                );

        // if user already valid
        if(confirmedToken.getConfirmedAt() != null) {
            throw new IllegalStateException("User already varified");
        }
    }
}
