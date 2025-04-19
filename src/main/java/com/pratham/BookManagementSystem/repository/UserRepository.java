package com.pratham.BookManagementSystem.repository;

import com.pratham.BookManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
