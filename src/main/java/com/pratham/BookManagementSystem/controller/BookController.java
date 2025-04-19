package com.pratham.BookManagementSystem.controller;

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
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/getBookById")
    public ResponseEntity<Book> getBookById(int bookId){
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/updaetBook")
    public ResponseEntity<Book> updateBook(int bookId, Book book){
        return new ResponseEntity<>(bookService.updateBook(bookId, book), HttpStatus.OK);
    }
    @DeleteMapping("/deleteBook")
    public ResponseEntity<String> deleteBook(int bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book Deleted Successfully", HttpStatus.OK);
    }


}
