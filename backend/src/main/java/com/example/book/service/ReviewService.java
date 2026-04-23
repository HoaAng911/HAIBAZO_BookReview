package com.example.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.model.Book;
import com.example.book.model.Review;
import com.example.book.model.User;
import com.example.book.repository.ReviewRepo;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
  @Autowired
  private ReviewRepo reviewRepo;
  @Autowired
  private AuthService authService;
  @Autowired
  private BookService bookService;

  public List<Review> getReviewsByBook(Long bookId) {
    return reviewRepo.findByBookId(bookId);
  }

  @Transactional
  public Review createReview(Long userId, Long bookId, Review review) {
    if (reviewRepo.existsByUserIdAndBookId(userId, bookId)) {
      throw new RuntimeException("Already reviewed");
    }
    User user = authService.getUserById(userId);
    Book book = bookService.getBookById(bookId);
    review.setUser(user);
    review.setBook(book);
    Review saved = reviewRepo.save(review);
    updateAverageRating(bookId);
    return saved;
  }

  private void updateAverageRating(Long bookId) {
    List<Review> reviews = reviewRepo.findByBookId(bookId);
    double avg = reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    bookService.updateBookRating(bookId, Math.round(avg * 10) / 10.0);
  }

  public void deleteReview(long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteReview'");
  }
}
