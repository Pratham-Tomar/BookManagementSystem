package com.pratham.BookManagementSystem.service;

import com.pratham.BookManagementSystem.config.EmailService;
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
import org.slf4j.LoggerFactory;
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

    @Autowired
    private EmailService emailService;

    private static final org.slf4j.Logger log= LoggerFactory.getLogger(TransactionService.class);


    @PreAuthorize("hasRole('ADMIN')")
    public List<TransactionDto> getAllTransactions() {
        log.info("Fetching all transactions");
        List<Transaction> transactions= transactionRepository.findAll();
        return transactions.stream().map(TransactionMapper::toDto).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TransactionDto getTransactionById(long transactionId){
        log.info("Fetching transaction with ID: {}", transactionId);
        Transaction transaction= transactionRepository.findById(transactionId).orElseThrow(()->new RuntimeException("Transaction not found with id: " + transactionId));
        log.debug("Fetched transaction details: {}", transaction);
        return TransactionMapper.toDto(transaction);
    }

    public TransactionDto borrowBook(int userId, int bookId,
                                  String remarks)  {

        UserDto userDto = userService.getUserById(userId);
        if (userDto == null) {
            log.info("User not found with id: {}", userId);
            throw new RuntimeException("User not found with id: " + userId);
        }

        BookDto bookDto = bookService.getBookById(bookId);


        if (bookDto == null) {
            log.info("Book not found with id: {}", bookId);
            throw new RuntimeException("Book not found with id: " + bookId);
        }

        Transaction transaction = new Transaction();

         if(bookDto.getAvailableCopies() <= 0) {
            log.info("No available copies for the book: {}", bookDto.getBookName());
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

        // Format the email content
        String emailContent = String.format(
                "Dear %s,\n\n" +
                        "You have successfully borrowed the book titled: *\"%s\"* from the NexusVerse Library.\n\n" +
                        "📖 Book Details:\n" +
                        "📚 Title: %s\n" +
                        "✍️ Author: %s\n" +
                        "🏢 Publisher: %s\n" +
                        "📅 Publication Date: %s\n" +
                        "🔢 ISBN: %s\n" +
                        "📄 Pages: %d\n" +
                        "🗣️ Language: %s\n" +
                        "🎭 Genre: %s\n" +
                        "💰 Price: ₹%.2f\n\n" +
                        "📅 Borrow Date: %s\n" +
                        "📚 Due Date: %s\n\n" +
                        "Please make sure to return the book by the due date to avoid any late fees.\n\n" +
                        "Happy Reading!\n" +
                        "NexusVerse Team\n" +
                        "📧 support@nexusverse.com",
                userDto.getUsername(),
                bookDto.getBookName(),
                bookDto.getBookName(),
                bookDto.getAuthorName(),
                bookDto.getPublisherName(),
                bookDto.getPublicationDate(),
                bookDto.getIsbnNumber(),
                bookDto.getNumberOfPages(),
                bookDto.getLanguage(),
                bookDto.getGenre(),
                bookDto.getPrice(),
                borrowDate,
                returnDate
        );
        log.info("Sending email to user: {}", userDto.getEmail());
        emailService.sendEmailAsync(userDto.getEmail(), "Book Borrowed", emailContent);


        return TransactionMapper.toDto(savedTransaction);
    }

    public TransactionDto returnBook(int userId ,int bookId , String remarks){

        UserDto userDto= userService.getUserById(userId);
        if(userDto==null){
            log.info("User not found with id: {}", userId);
            throw new RuntimeException("User not found with id: " + userId);
        }

        BookDto bookDto = bookService.getBookById(bookId);
        if(bookDto==null){
            log.info("Book not found with id: {}", bookId);
            throw new RuntimeException("Book not found with id: " + bookId);
        }

        Transaction borrowTransaction = transactionRepository
                .findByUserIdAndBook_BookIdAndStatus(userId, bookId, Status.BORROWED)
                .orElseThrow(() ->{
                        log.info("No active borrowed transaction found for userId: {} and bookId: {}", userId, bookId);
        return new RuntimeException("No active borrowed transaction found for this user and book.");
        });

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        double fine=0.0;
        if(today.isAfter(borrowTransaction.getReturnDate())){
            log.info("Book is overdue. Calculating fine.");
            long overdueDays = borrowTransaction.getReturnDate().until(today).getDays(); // or ChronoUnit.DAYS.between(...)
            log.info("Overdue days: {}", overdueDays);
            fine = overdueDays * 10.0;  // ₹10 per day
            log.info("Total fine amount: ₹{}", fine);
            borrowTransaction.setRemarks(remarks + " | Fine: ₹" + fine);
            log.info("Remarks updated with fine details: {}", borrowTransaction.getRemarks());
        }
        else{
            log.info("Book returned on time.");
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

        String emailContent = String.format(
                "Dear %s,\n\n" +
                        "We are glad to inform you that the book titled: *\"%s\"* has been successfully returned to the NexusVerse Library.\n\n" +
                        "📚 Book Details:\n" +
                        "  - **Title**: %s\n" +
                        "  - **Author**: %s\n" +
                        "  - **Publisher**: %s\n" +
                        "  - **Publication Date**: %s\n" +
                        "  - **ISBN**: %s\n" +
                        "  - **Pages**: %d\n" +
                        "  - **Language**: %s\n" +
                        "  - **Genre**: %s\n\n" +
                        "We hope you enjoyed reading the book. Thank you for returning it on time. If you'd like to borrow more books, please visit our [NexusVerse Library](#).\n\n" +
                        "Your feedback is important to us! Feel free to share your reading experience.\n\n" +
                        "📧 For any queries, contact us at support@nexusverse.com\n\n" +
                        "Best Regards,\n" +
                        "The NexusVerse Library Team\n" +
                        "www.nexusverse.com"
                , userDto.getUsername(),
                bookDto.getBookName(),
                bookDto.getBookName(),
                bookDto.getAuthorName(),
                bookDto.getPublisherName(),
                bookDto.getPublicationDate(),
                bookDto.getIsbnNumber(),
                bookDto.getNumberOfPages(),
                bookDto.getLanguage(),
                bookDto.getGenre(),
                bookDto.getPrice()
        );
        log.info("Sending email to user for returning Book: {}", userDto.getEmail());
        emailService.sendEmailAsync(userDto.getEmail(), "Book Returned Successfully", emailContent);
        return TransactionMapper.toDto(transaction);
    }
}
