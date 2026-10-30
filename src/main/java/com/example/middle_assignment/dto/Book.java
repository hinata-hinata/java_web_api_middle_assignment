package com.example.middle_assignment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer price;
    private LocalDateTime createdAt;
    private String review;
  }





