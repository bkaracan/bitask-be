package com.ilkayburak.bitask.repository;

import com.ilkayburak.bitask.entity.Token;
import com.ilkayburak.bitask.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByTokenValue(String tokenValue);

    List<Token> findByUserEmail(String email);

    List<Token> findByUser(User user);
}
