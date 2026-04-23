package com.example.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.model.Book;
import com.example.book.repository.BookRepo;

@Service
public class BookService {
  @Autowired
  private BookRepo bookRepo;

  public List<Book> getAllBook() {
    return bookRepo.findAll();
  }

  public Book getBookById(Long id) {
    return bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
  }

  public List<Book> searchBooks(String keyword) {
    List<Book> byTitle = bookRepo.findByTitle(keyword);
    List<Book> byAuthor = bookRepo.findByAuthor(keyword);
    byTitle.addAll(byAuthor);
    return byTitle.stream().distinct().toList();
  }

  @Transactional
  public Book createBook(Book book) {
    return bookRepo.save(book);
  }

  @Transactional
  public void deleteBook(Long id) {
    bookRepo.deleteById(id);
  }

  @Transactional
  public void updateBookRating(Long bookId, Double avgRating) {
    bookRepo.updateAvgRating(bookId, avgRating);
  }
}
