package com.pratham.BookManagementSystem.dtos;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {
    private int bookId;
    private String bookName;
    private String authorName;
    private String publisherName;
    private LocalDate publicationDate;
    private String isbnNumber;
    private int numberOfPages;
    private int totalCopies;
    private int availableCopies;
    private String language;
    private String genre;
    private double price;



}
