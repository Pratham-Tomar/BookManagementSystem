package com.pratham.BookManagementSystem.controller;

import com.pratham.BookManagementSystem.dtos.TransactionDto;
import com.pratham.BookManagementSystem.entity.Transaction;
import com.pratham.BookManagementSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<TransactionDto>> getAllTransaction() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/getTransactionById/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable  long transactionId) {
        return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
    }

    @PostMapping("/borrowBook/{userId}/{bookId}")
    public ResponseEntity<TransactionDto> borrowBook(@PathVariable int userId, @PathVariable int bookId,
                                                  @RequestBody String remarks) {
        return ResponseEntity.ok(transactionService.borrowBook(userId, bookId, remarks));
    }

    @PostMapping("/returnBook/{userId}/{bookId}")
    public ResponseEntity<TransactionDto> returnBook(@PathVariable int userId, @PathVariable int bookId,
                                                  @RequestBody String remarks) {
        return ResponseEntity.ok(transactionService.returnBook(userId, bookId, remarks));
    }



}
