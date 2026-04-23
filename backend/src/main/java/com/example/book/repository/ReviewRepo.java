package com.example.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.Review;
import com.example.book.model.User;

public interface ReviewRepo extends JpaRepository<Review, Long> {
  List<Review> findByBookId(Long bookId);

  boolean existsByUserIdAndBookId(Long userId, Long bookId);

}