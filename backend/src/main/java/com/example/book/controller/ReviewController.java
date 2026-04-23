package com.example.book.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.model.Review;
import com.example.book.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {
  @Autowired
  private ReviewService reviewService;

  @GetMapping("/book/{bookId}")
  public List<Review> getBookReviews(@PathVariable Long bookId) {
    return reviewService.getReviewsByBook(bookId);
  }

  @PostMapping
  public Review createReview(@RequestBody Map<String, Object> body) {
    Long userId = Long.valueOf(body.get("userId").toString());
    Long bookId = Long.valueOf(body.get("bookId").toString());
    Review review = new Review();
    review.setRating((Integer) body.get("rating"));
    review.setComment((String) body.get("comment"));
    return reviewService.createReview(userId, bookId, review);
  }

  @DeleteMapping("/{id}")
  public void deleteReview(@PathVariable long id) {
    reviewService.deleteReview(id);
  }
}
