package com.example.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}