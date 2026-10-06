package com.example.middle_assignment.repository;

import com.example.middle_assignment.dto.Book;
import com.example.middle_assignment.mapper.BookMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

  private final BookMapper bookMapper;

  public BookRepository(BookMapper bookMapper) {
    this.bookMapper = bookMapper;
  }

  public List<Book> findAll() {
    return bookMapper.selectAll();
  }
}
