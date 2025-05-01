package com.pratham.BookManagementSystem.service;

import com.pratham.BookManagementSystem.entity.Book;
import com.pratham.BookManagementSystem.entity.Transaction;
import com.pratham.BookManagementSystem.entity.User;
import com.pratham.BookManagementSystem.enums.Status;
import com.pratham.BookManagementSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;


    @PreAuthorize("hasRole('ADMIN')")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Transaction getTransactionById(long transactionId){
        return transactionRepository.findById(transactionId).orElseThrow(()->new RuntimeException("Transaction not found with id: " + transactionId));
    }

    public Transaction borrowBook(int userId, int bookId,
                                  String remarks)  {

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        Book book = bookService.getBookById(bookId);

        if (book == null) {
            throw new RuntimeException("Book not found with id: " + bookId);
        }

        Transaction transaction = new Transaction();

         if(book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Invalid number of available copies for the book: " + book.getBookName());
        }
        transaction.setStatus(Status.BORROWED);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookService.updateBook(bookId,book);


        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(7);  // Assuming a 7-day borrowing period
        LocalTime borrowTime = LocalTime.now();

        transaction.setUser(user);
        transaction.setBook(book);
        transaction.setBorrowDate(borrowDate);
        transaction.setBorrowTime(borrowTime);
        transaction.setReturnDate(returnDate);
        transaction.setReturnTime(null);  // Set to null initially



        transaction.setRemarks(remarks);

        return transactionRepository.save(transaction);
    }






}
