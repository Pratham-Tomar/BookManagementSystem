package com.pratham.BookManagementSystem.service;

import com.pratham.BookManagementSystem.dtos.BookDto;
import com.pratham.BookManagementSystem.entity.Book;
import com.pratham.BookManagementSystem.exception.BookNotFoundException;
import com.pratham.BookManagementSystem.mapper.BookMapper;
import com.pratham.BookManagementSystem.repository.BookRepository;
import org.slf4j.LoggerFactory;
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

    private static final org.slf4j.Logger log= LoggerFactory.getLogger(BookService.class);

    //CRUD for the admin only
    @PreAuthorize("hasRole('ADMIN')")
    public List<BookDto> getAllBooks() {
        log.info("Fetching all books");
        List<Book> books= bookRepository.findAll();
        return books.stream().map(BookMapper::toDto).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto getBookById(int bookId){
        log.info("Fetching book with ID: {}", bookId);
        Book book= bookRepository.findById(bookId).orElseThrow(()->{
            log.error("Book not found with ID: {}", bookId);
            return new BookNotFoundException(bookId);
        });
        log.debug("Fetched book details: {}", book);
        return BookMapper.toDto(book);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public BookDto addBook(BookDto bookDto) {
        log.info("Adding a new book: {}", bookDto.getBookName());
        Book book = BookMapper.toEntity(bookDto);
        Book savedBook=bookRepository.save(book);
        log.info("Book added successfully with ID: {}", savedBook.getBookId());
        return BookMapper.toDto(savedBook);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto updateBook(int bookId , BookDto bookDto){
        log.info("Updating the book with bookId: {} and BookDto: {}", bookId, bookDto);
        Book book=BookMapper.toEntity(bookDto);
        Book bookExists=bookRepository.findById(bookId).orElseThrow(()-> {
            log.error("Book not found in updateBook with ID: {}", bookId);
            return new BookNotFoundException(bookId);
        });
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
        log.info("deleting the Book with the bookId: {}",bookId);
        Book bookExists=bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException(bookId));
        log.info("Book with bookId: {} deleted",bookId);
        bookRepository.delete(bookExists);
    }

    public List<BookDto> getAllBookdetails(){
        log.info("fetching all the details of the Books");
        List<Book> books=bookRepository.findAll();
        log.info("Book details Fetched");
        return books.stream().map(BookMapper::toDto).collect(Collectors.toList());
    }

    public BookDto UserGetBookById(int bookId){
        log.info("Fetching the Book details with bookId: {}",bookId);
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException(bookId));
        log.info("Book details with BookId:{} fetched",bookId);
        return BookMapper.toDto(book);
    }










}
