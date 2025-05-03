package com.pratham.BookManagementSystem.dtos;

import com.pratham.BookManagementSystem.entity.Book;
import com.pratham.BookManagementSystem.entity.User;
import com.pratham.BookManagementSystem.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {

    private long transactionId;
    private UserDto userDto;
    private BookDto bookDto;
    private LocalDate borrowDate;
    private LocalTime borrowTime;
    private LocalDate returnDate;
    private LocalTime returnTime;
    private Status status;
    private double fineAmount;
    private String remarks;
}
