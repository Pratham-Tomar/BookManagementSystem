package com.pratham.BookManagementSystem.exception;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(int bookId ) {
    super("Book not found with id: " + bookId);
  }
}
