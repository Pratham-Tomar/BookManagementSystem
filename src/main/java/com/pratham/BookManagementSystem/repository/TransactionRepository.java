package com.pratham.BookManagementSystem.repository;

import com.pratham.BookManagementSystem.entity.Transaction;
import com.pratham.BookManagementSystem.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

     Optional<Transaction> findByUserIdAndBookIdAndStatus(int userId, int bookId, Status status);
}
