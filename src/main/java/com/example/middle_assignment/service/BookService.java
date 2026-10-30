package com.example.middle_assignment.service;

import com.example.middle_assignment.dto.Book;
import com.example.middle_assignment.exception.BookArgumentNotValidException;
import com.example.middle_assignment.exception.BookNotFoundException;
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

  public Book saveBook(Book book) {
    //titleが殻の場合のバリデーション
    if(book.getTitle() == null || book.getTitle().isEmpty()){
      throw new BookArgumentNotValidException("Title is required");
    }

    if(book.getAuthor() == null || book.getAuthor().isEmpty()){
      throw new BookArgumentNotValidException("Author is required");
    }

    if(book.getPrice() == null){
      throw new BookArgumentNotValidException("Price is required");
    }

    bookRepository.insertBook(book);
    return book;
  }

  public Book findById(long id) {
    Book book = bookRepository.findById(id);

    if (book == null) {
      throw new BookNotFoundException(id);
    }

    return book;
  }

  public boolean delete(long id)  {
    if(!bookRepository.delete(id)){
      throw new BookNotFoundException(id);
    }

    return true;
  }

  public Book update(Book book, long id) {
    //titleが殻の場合のバリデーション
    if(book.getTitle() == null || book.getTitle().isEmpty()){
      throw new BookArgumentNotValidException("Title is required");
    }

    if(book.getAuthor() == null || book.getAuthor().isEmpty()){
      throw new BookArgumentNotValidException("Author is required");
    }

    if(book.getPrice() == null){
      throw new BookArgumentNotValidException("Price is required");
    }

    //指定されたidが存在しないとき
    Book targetBook = bookRepository.findById(id);
    if(targetBook == null){
      throw new BookNotFoundException(id);
    }

    book.setId(id);
    book.setCreatedAt(targetBook.getCreatedAt());
    return bookRepository.update(book);
  }

  public List<Book> searchByTitle(String title) {
    if (title == null || title.isBlank()) {
      throw new BookArgumentNotValidException(
              "Search title is required"
      );
    }

    return bookRepository.searchByTitle(title.trim());
  }

  public List<Book> searchByPrice(int minPrice, int maxPrice) {
    if (minPrice < 0 || maxPrice < 0) {
      throw new org.springframework.web.server.ResponseStatusException(
              org.springframework.http.HttpStatus.BAD_REQUEST,
              "価格は0以上で指定してください"
      );
    }

    if (minPrice > maxPrice) {
      throw new org.springframework.web.server.ResponseStatusException(
              org.springframework.http.HttpStatus.BAD_REQUEST,
              "最低価格は最高価格以下にしてください"
      );
    }

    return bookRepository.searchByPrice(minPrice, maxPrice);
  }
}
