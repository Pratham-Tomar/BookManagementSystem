package com.pratham.BookManagementSystem.controller;

import com.pratham.BookManagementSystem.dtos.BookDto;
import com.pratham.BookManagementSystem.dtos.UserDto;
import com.pratham.BookManagementSystem.entity.Book;
import com.pratham.BookManagementSystem.entity.User;
import com.pratham.BookManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/getAllUsers")
    public ResponseEntity<List<UserDto>> adminGetAllUsers(){
        return new ResponseEntity<>(userService.AdmingetAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/admin/getUserById/{userId}")
    public ResponseEntity<UserDto> adminGetUserById(@PathVariable int userId){
        return new ResponseEntity<>(userService.AdmingetUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/admin/addUser")
    public ResponseEntity<UserDto> adminAddUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.AdminAddUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/admin/updateUser/{userId}")
    public ResponseEntity<UserDto> adminUpdateUser(@PathVariable int userId,@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.AdminUpdateUserById(userId, userDto), HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteUserById/{userId}")
    public ResponseEntity<String> adminDeleteUserById(@PathVariable  int userId){
        userService.AdminDeleteUserById(userId);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteUserByName")
    public ResponseEntity<String> adminDeleteUserByName(@RequestParam  String userName){
        userService.AdminDeleteUserByName(userName);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

    // from here User can register himself , get his details by name and Id &  update his details

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }
    @GetMapping("/getUserByName")
    public ResponseEntity<UserDto> getUserByName(@RequestParam String userName){
        return new ResponseEntity<>(userService.getUserByName(userName), HttpStatus.OK);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable  int userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable int userId,@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUserById(userId, userDto), HttpStatus.OK);
    }

    @GetMapping("/getallbooks")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return new ResponseEntity<>(userService.getAllBooks(), HttpStatus.OK);
    }











}
