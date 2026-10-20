package com.example.middle_assignment.repository;

import com.example.middle_assignment.dto.Book;
import com.example.middle_assignment.exception.BookNotFoundException;
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

  public int insertBook(Book book) {
    return bookMapper.insert(book);
  }

  public Book findById(long id){
    return bookMapper.findById(id);
  }

  public boolean delete(long id){
    return bookMapper.delete(id) == 1;
  }

  public Book update(Book book) {
    if (bookMapper.update(book) == 0) {
      throw new BookNotFoundException(book.getId());
    }

    return book;
  }
}
