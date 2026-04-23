package com.example.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public Book createBook(Book book) {
    return bookRepo.save(book);
  }

  public void deleteBook(Long id) {
    bookRepo.deleteById(id);
  }

  public void updateBookRating(Long bookId,Double avgRating){
    Book book = getBookById(bookId);
    book.setAvgRating(avgRating);
    bookRepo.save(book);
  }
}
