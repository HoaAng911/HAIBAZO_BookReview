package com.example.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.model.Book;
import com.example.book.model.User;

public interface BookRepo extends JpaRepository<Book, Long> {
  List<Book> findByTitle(String title);

  List<Book> findByAuthor(String author);

  @Modifying
  @Transactional
  @Query("UPDATE Book b SET b.avgRating = :avgRating WHERE b.id = :bookId")
  void updateAvgRating(@Param("bookId") Long bookId, @Param("avgRating") Double avgRating);
}