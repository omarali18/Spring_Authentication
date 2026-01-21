package com.example.auth_token.service;

import com.example.auth_token.entity.TokenEntity;
import com.example.auth_token.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Optional<TokenEntity> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public TokenEntity save(TokenEntity token) {
        return tokenRepository.save(token);
    }
}
