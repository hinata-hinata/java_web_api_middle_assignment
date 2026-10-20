package com.example.middle_assignment.exception;

public class BookArgumentNotValidException extends RuntimeException {
  public BookArgumentNotValidException(String message) {
    super(message);
  }
}
