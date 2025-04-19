package com.pratham.BookManagementSystem.repository;

import com.pratham.BookManagementSystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
