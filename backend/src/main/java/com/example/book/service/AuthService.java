package com.example.book.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.book.model.User;
import com.example.book.repository.UserRepo;

@Service
public class AuthService {
  @Autowired
  private UserRepo userRepo;

  public User register(User user) {
    if (userRepo.existsByUsername(user.getUsername())) {
      throw new RuntimeErrorException(null, "Username exists");

    }
    if (userRepo.existsByEmail(user.getEmail())) {
      throw new RuntimeErrorException(null, "Email exists");
    }
    return userRepo.save(user);
  }

  public User login(String username, String password) {
    {
      return userRepo.findByUsername(username)
          .filter(u -> u.getPassword().equals(password))
          .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
  }

  public User getUserById(long id) {
    return userRepo.findById(id).orElse(null);
  }
}
