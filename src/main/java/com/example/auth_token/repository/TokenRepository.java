package com.example.auth_token.repository;

import com.example.auth_token.entity.TokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<TokenEntity, Long> {
//    @Override
    Optional<TokenEntity> findByToken(String token);
}
