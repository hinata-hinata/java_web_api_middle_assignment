package com.example.middle_assignment.exception;




public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(long id) {
    super("指定されたidのノートが見つかりません" + id);
  }
}
