package com.example.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.model.Book;
import com.example.book.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
  @Autowired
  private BookService bookService;

  @GetMapping
  public List<Book> getAllBook() {
    return bookService.getAllBook();
  }

  @GetMapping("/{id}")
  public Book getBook(@PathVariable Long id) {
    return bookService.getBookById(id);
  }

  @GetMapping("/search")
  public List<Book> searchBooks(@RequestParam String q) {
    return bookService.searchBooks(q);
  }

  @PostMapping
  public Book createBook(@RequestBody Book book) {
    return bookService.createBook(book);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
  }
}
