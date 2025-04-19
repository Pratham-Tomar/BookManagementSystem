package com.pratham.BookManagementSystem.service;

import com.pratham.BookManagementSystem.entity.Book;
import com.pratham.BookManagementSystem.exception.BookNotFoundException;
import com.pratham.BookManagementSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    //CRUD for the admin only
    @PreAuthorize("hasRole('ADMIN')")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Book getBookById(int bookId){
        return bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException(bookId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Book updateBook(int bookId , Book book){
        Book bookExists=bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException(bookId));
        bookExists.setBookName(book.getBookName());
        bookExists.setAuthorName(book.getAuthorName());
        bookExists.setGenre(book.getGenre());
        bookExists.setLanguage(book.getLanguage());
        bookExists.setNumberOfPages(book.getNumberOfPages());
        bookExists.setPublisherName(book.getPublisherName());
        bookExists.setPublicationDate(book.getPublicationDate());
        bookExists.setPrice(book.getPrice());
        bookExists.setIsbnNumber(book.getIsbnNumber());
        bookExists.setTotalCopies(book.getTotalCopies());
        bookExists.setAvailableCopies(book.getAvailableCopies());
        return bookRepository.save(bookExists);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(int bookId) {
        Book bookExists=bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException(bookId));
        bookRepository.delete(bookExists);
    }








}
