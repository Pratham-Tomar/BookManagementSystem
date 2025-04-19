package com.pratham.BookManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    public Book() {}
    public Book(int bookId,String bookName,String authorName,String publisherName,LocalDate publicationDate, String isbnNumber,int totalCopies,int availableCopies,int numberOfPages,String language,String genre,double price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publicationDate = publicationDate;
        this.isbnNumber = isbnNumber;
        this.numberOfPages = numberOfPages;
        this.totalCopies=totalCopies;
        this.availableCopies=availableCopies;
        this.language = language;
        this.genre = genre;
        this.price = price;
    }
    //getters for the values

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getTotalCopies(){
        return totalCopies;
    }

    public int getAvailableCopies(){
        return availableCopies;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public String getLanguage() {
        return language;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public void setBookId(int bookId){
        this.bookId=bookId;
    }

    public void setBookName(String bookName){
        this.bookName=bookName;
    }

    public void setAuthorName(String authorName){
        this.authorName=authorName;
    }

    public void setPublicationDate(LocalDate publicationDate){
        this.publicationDate=publicationDate;
    }

    public void setPublisherName(String publisherName){
        this.publisherName=publisherName;
    }
    public void setIsbnNumber(String isbnNumber){
        this.isbnNumber=isbnNumber;
    }

    public void setNumberOfPages(int numberOfPages){
        this.numberOfPages=numberOfPages;
    }

    public void setTotalCopies(int totalCopies){
        this.totalCopies=totalCopies;
    }

    public void setAvailableCopies(int availableCopies){
        this.availableCopies=availableCopies;
    }

    public  void setLanguage(String language){
        this.language=language;
    }

    public void setGenre(String genre){
        this.genre=genre;
    }

    public void setPrice(double price){
        this.price=price;
    }
}
