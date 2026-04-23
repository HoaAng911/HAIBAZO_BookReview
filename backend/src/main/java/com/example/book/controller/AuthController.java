package com.example.book.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.model.User;
import com.example.book.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public User register(@RequestBody User user) {
    return authService.register(user);
  }

  @PostMapping("/login")
  public User login(@RequestBody Map<String, String> body) {
    return authService.login(body.get("username"), body.get("password"));
  }
}
