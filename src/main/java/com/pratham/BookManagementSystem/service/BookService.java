package com.pratham.BookManagementSystem.service;

import com.pratham.BookManagementSystem.dtos.BookDto;
import com.pratham.BookManagementSystem.entity.Book;
import com.pratham.BookManagementSystem.exception.BookNotFoundException;
import com.pratham.BookManagementSystem.mapper.BookMapper;
import com.pratham.BookManagementSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    //CRUD for the admin only
    @PreAuthorize("hasRole('ADMIN')")
    public List<BookDto> getAllBooks() {
        List<Book> books= bookRepository.findAll();
        return books.stream().map(BookMapper::toDto).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto getBookById(int bookId){
        Book book= bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException(bookId));
        return BookMapper.toDto(book);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public BookDto addBook(BookDto bookDto) {
        Book book = BookMapper.toEntity(bookDto);
        Book savedBook=bookRepository.save(book);
        return BookMapper.toDto(savedBook);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto updateBook(int bookId , BookDto bookDto){
        Book book=BookMapper.toEntity(bookDto);
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
         Book finalBook=bookRepository.save(bookExists);
         return BookMapper.toDto(finalBook);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(int bookId) {
        Book bookExists=bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException(bookId));
        bookRepository.delete(bookExists);
    }

    public List<BookDto> getAllBookdetails(){
        List<Book> books=bookRepository.findAll();
        return books.stream().map(BookMapper::toDto).collect(Collectors.toList());
    }

    public BookDto UserGetBookById(int bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException(bookId));
        return BookMapper.toDto(book);
    }










}
