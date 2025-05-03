package com.pratham.BookManagementSystem.service;

import com.pratham.BookManagementSystem.dtos.BookDto;
import com.pratham.BookManagementSystem.dtos.TransactionDto;
import com.pratham.BookManagementSystem.dtos.UserDto;
import com.pratham.BookManagementSystem.entity.Book;
import com.pratham.BookManagementSystem.entity.Transaction;
import com.pratham.BookManagementSystem.entity.User;
import com.pratham.BookManagementSystem.enums.Status;
import com.pratham.BookManagementSystem.mapper.BookMapper;
import com.pratham.BookManagementSystem.mapper.TransactionMapper;
import com.pratham.BookManagementSystem.mapper.UserMapper;
import com.pratham.BookManagementSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;


    @PreAuthorize("hasRole('ADMIN')")
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions= transactionRepository.findAll();
        return transactions.stream().map(TransactionMapper::toDto).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TransactionDto getTransactionById(long transactionId){
        Transaction transaction= transactionRepository.findById(transactionId).orElseThrow(()->new RuntimeException("Transaction not found with id: " + transactionId));
        return TransactionMapper.toDto(transaction);
    }

    public TransactionDto borrowBook(int userId, int bookId,
                                  String remarks)  {

        UserDto userDto = userService.getUserById(userId);
        if (userDto == null) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        BookDto bookDto = bookService.getBookById(bookId);


        if (bookDto == null) {
            throw new RuntimeException("Book not found with id: " + bookId);
        }

        Transaction transaction = new Transaction();

         if(bookDto.getAvailableCopies() <= 0) {
            throw new RuntimeException("Invalid number of available copies for the book: " + bookDto.getBookName());
        }
        transaction.setStatus(Status.BORROWED);
        bookDto.setAvailableCopies(bookDto.getAvailableCopies() - 1);
        bookService.updateBook(bookId,bookDto);


        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(7);  // Assuming a 7-day borrowing period
        LocalTime borrowTime = LocalTime.now();

        transaction.setUser(UserMapper.toEntity(userDto));
        transaction.setBook(BookMapper.toEntity(bookDto));
        transaction.setBorrowDate(borrowDate);
        transaction.setBorrowTime(borrowTime);
        transaction.setReturnDate(returnDate);
        transaction.setReturnTime(null);  // Set to null initially


        transaction.setRemarks(remarks);

        Transaction savedTransaction =transactionRepository.save(transaction);
        return TransactionMapper.toDto(savedTransaction);
    }

    public TransactionDto returnBook(int userId ,int bookId , String remarks){

        UserDto userDto= userService.getUserById(userId);
        if(userDto==null){
            throw new RuntimeException("User not found with id: " + userId);
        }

        BookDto bookDto = bookService.getBookById(bookId);
        if(bookDto==null){
            throw new RuntimeException("Book not found with id: " + bookId);
        }

        Transaction borrowTransaction = transactionRepository
                .findByUserIdAndBook_BookIdAndStatus(userId, bookId, Status.BORROWED)
                .orElseThrow(() -> new RuntimeException("No active borrowed transaction found for this user and book."));

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        double fine=0.0;
        if(today.isAfter(borrowTransaction.getReturnDate())){
            long overdueDays = borrowTransaction.getReturnDate().until(today).getDays(); // or ChronoUnit.DAYS.between(...)
            fine = overdueDays * 10.0;  // ₹10 per day
            borrowTransaction.setRemarks(remarks + " | Fine: ₹" + fine);
        }
        else{
            borrowTransaction.setRemarks(remarks);
        }
        borrowTransaction.setStatus(Status.RETURNED);
        borrowTransaction.setReturnDate(today);
        borrowTransaction.setReturnTime(now);
        borrowTransaction.setFineAmount(fine);

        // Update book stock
        bookDto.setAvailableCopies(bookDto.getAvailableCopies() + 1);
        bookService.updateBook(bookId, bookDto);

        Transaction transaction= transactionRepository.save(borrowTransaction);
        return TransactionMapper.toDto(transaction);
    }








}
