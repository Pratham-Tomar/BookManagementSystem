package com.pratham.BookManagementSystem.entity;

import com.pratham.BookManagementSystem.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.Column;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    private long transactionId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    private LocalDate borrowDate;
    private LocalTime borrowTime;
    private LocalDate returnDate;
    private LocalTime returnTime;
    @Enumerated(EnumType.STRING)
    private Status status;
    private double fineAmount;
    private String remarks;







}
