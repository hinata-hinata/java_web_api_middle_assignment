package com.example.middle_assignment.controller;

import com.example.middle_assignment.dto.Book;
import com.example.middle_assignment.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<Book>> findAllBooks() {
    List<Book> books = bookService.selectAll();

    return ResponseEntity.ok(books);
  }

  @PostMapping
  public ResponseEntity<Book> saveBook(@RequestBody Book book,
                                       UriComponentsBuilder uriBuilder) {
    Book createdBook = bookService.saveBook(book);

    URI location = uriBuilder.path("/api/books/{id}")
            .buildAndExpand(createdBook.getId()).toUri();
    
    return ResponseEntity.created(location).body(createdBook);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> findById(@PathVariable Long id) {
    Book book = bookService.findById(id);

    return ResponseEntity.ok(book);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    bookService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}")
  public ResponseEntity<Book> update(@PathVariable long id,
                                     @RequestBody Book book) {
    bookService.update(book, id);
    return ResponseEntity.ok(book);
  }

  @GetMapping("/search")
  public ResponseEntity<List<Book>> searchBooks(
          @RequestParam("title") String title) {

    List<Book> books = bookService.searchByTitle(title);

    return ResponseEntity.ok(books);
  }
  }
