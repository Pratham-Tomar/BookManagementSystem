package com.pratham.BookManagementSystem.entity;

import com.pratham.BookManagementSystem.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.Column;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Transaction {
    @Id
    private long transactionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    private LocalDate borrowDate;
    private LocalTime borrowTime;
    private LocalDate returnDate;
    private LocalTime returnTime;
    @Enumerated(EnumType.STRING)
    private Status status;
    private double fineAmount;
    private String remarks;

    public Transaction(){}

    public Transaction(long transactionId, LocalDate borrowDate, LocalTime borrowTime, LocalDate returnDate, LocalTime returnTime, Status status, double fineAmount, String remarks) {
        this.transactionId = transactionId;
        this.borrowDate = borrowDate;
        this.borrowTime = borrowTime;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
        this.status = status;
        this.fineAmount = fineAmount;
        this.remarks = remarks;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalTime getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(LocalTime borrowTime) {
        this.borrowTime = borrowTime;
    }

    public LocalTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalTime returnTime) {
        this.returnTime = returnTime;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
}
