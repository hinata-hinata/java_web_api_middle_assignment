package com.example.middle_assignment.service;

import com.example.middle_assignment.dto.Book;
import com.example.middle_assignment.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> selectAll() {
    return bookRepository.findAll();
  }
}
