package com.pratham.BookManagementSystem.repository;

import com.pratham.BookManagementSystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
