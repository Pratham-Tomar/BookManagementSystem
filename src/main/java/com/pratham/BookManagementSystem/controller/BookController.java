package com.pratham.BookManagementSystem.controller;

import com.pratham.BookManagementSystem.dtos.BookDto;
import com.pratham.BookManagementSystem.entity.Book;
import com.pratham.BookManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/getBookById/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable int bookId) {
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateBook/{bookId}")
    public ResponseEntity<BookDto> updateBook(@PathVariable int bookId,@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.updateBook(bookId, bookDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/user/getAllBookDetails")
    public ResponseEntity<List<BookDto>> getAllBookDetails() {
        return new ResponseEntity<>(bookService.getAllBookdetails(), HttpStatus.OK);
    }

    @GetMapping("/user/getBookById/{bookId}")
    public ResponseEntity<BookDto> UserGetBookById(@PathVariable  int bookId) {
        return new ResponseEntity<>(bookService.UserGetBookById(bookId), HttpStatus.OK);
    }


}
