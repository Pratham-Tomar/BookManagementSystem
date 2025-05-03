package com.pratham.BookManagementSystem.mapper;

import com.pratham.BookManagementSystem.dtos.TransactionDto;
import com.pratham.BookManagementSystem.entity.Transaction;

public class TransactionMapper {

    public static TransactionDto toDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setTransactionId(transaction.getTransactionId());
        transactionDto.setUserDto(UserMapper.toDto(transaction.getUser()));
        transactionDto.setBookDto(BookMapper.toDto(transaction.getBook()));
        transactionDto.setBorrowDate(transaction.getBorrowDate());
        transactionDto.setBorrowTime(transaction.getBorrowTime());
        transactionDto.setReturnDate(transaction.getReturnDate());
        transactionDto.setReturnTime(transaction.getReturnTime());
        transactionDto.setStatus(transaction.getStatus());
        transactionDto.setFineAmount(transaction.getFineAmount());
        transactionDto.setRemarks(transaction.getRemarks());
        return transactionDto;
    }

    public static Transaction toEntity(TransactionDto transactionDto){
        Transaction transaction = new Transaction();

        transaction.setTransactionId(transactionDto.getTransactionId());
        transaction.setUser(UserMapper.toEntity(transactionDto.getUserDto()));
        transaction.setBook(BookMapper.toEntity(transactionDto.getBookDto()));
        transaction.setBorrowDate(transactionDto.getBorrowDate());
        transaction.setBorrowTime(transactionDto.getBorrowTime());
        transaction.setReturnDate(transactionDto.getReturnDate());
        transaction.setReturnTime(transactionDto.getReturnTime());
        transaction.setStatus(transactionDto.getStatus());
        transaction.setFineAmount(transactionDto.getFineAmount());
        transaction.setRemarks(transactionDto.getRemarks());
        return transaction;
    }
}
