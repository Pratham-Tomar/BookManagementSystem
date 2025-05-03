package com.pratham.BookManagementSystem.mapper;

import com.pratham.BookManagementSystem.dtos.BookDto;
import com.pratham.BookManagementSystem.entity.Book;

public class BookMapper {

    public static BookDto toDto(Book book){
       BookDto bookDto = new BookDto();

        bookDto.setBookId(book.getBookId());
        bookDto.setBookName(book.getBookName());
        bookDto.setAuthorName(book.getAuthorName());
        bookDto.setPublisherName(book.getPublisherName());
        bookDto.setPublicationDate(book.getPublicationDate());
        bookDto.setIsbnNumber(book.getIsbnNumber());
        bookDto.setLanguage(book.getLanguage());
        bookDto.setGenre(book.getGenre());
        bookDto.setNumberOfPages(book.getNumberOfPages());
        bookDto.setPrice(book.getPrice());
        bookDto.setTotalCopies(book.getTotalCopies());
        bookDto.setAvailableCopies(book.getAvailableCopies());
        return bookDto;
    }

    public static Book toEntity (BookDto bookDto){
        Book book = new Book();

        book.setBookId(bookDto.getBookId());
        book.setBookName(bookDto.getBookName());
        book.setAuthorName(bookDto.getAuthorName());
        book.setPublisherName(bookDto.getPublisherName());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setIsbnNumber(bookDto.getIsbnNumber());
        book.setLanguage(bookDto.getLanguage());
        book.setGenre(bookDto.getGenre());
        book.setNumberOfPages(bookDto.getNumberOfPages());
        book.setPrice(bookDto.getPrice());
        book.setTotalCopies(bookDto.getTotalCopies());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return book;
    }


}
