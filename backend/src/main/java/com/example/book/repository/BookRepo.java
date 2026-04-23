package com.example.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.Book;
import com.example.book.model.User;

public interface BookRepo extends JpaRepository<Book, Long> {
  List<Book> findByTitle(String title);

  List<Book>findByAuthor(String author);
}