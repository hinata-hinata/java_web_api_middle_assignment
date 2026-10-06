package com.example.middle_assignment.controller;

import com.example.middle_assignment.dto.Book;
import com.example.middle_assignment.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<Book>> findAllNotes() {
    List<Book> notes = bookService.selectAll();

    return ResponseEntity.ok(notes);
  }



}
