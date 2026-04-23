package com.example.book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String author;
  @Column(length = 2000)
  private String description;
  private String coverImage;
  private String category;
  private Double avgRating = 0.0;
  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  private List<Review> reviews;
}
