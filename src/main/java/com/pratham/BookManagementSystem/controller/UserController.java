package com.pratham.BookManagementSystem.controller;

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
    public ResponseEntity<List<User>> adminGetAllUsers(){
        return new ResponseEntity<>(userService.AdmingetAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/admin/getUserById/{userId}")
    public ResponseEntity<User> adminGetUserById(@PathVariable int userId){
        return new ResponseEntity<>(userService.AdmingetUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/admin/addUser")
    public ResponseEntity<User> adminAddUser(@RequestBody User user){
        return new ResponseEntity<>(userService.AdminAddUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/admin/updateUser/{userId}")
    public ResponseEntity<User> adminUpdateUser(@PathVariable int userId,@RequestBody User user){
        return new ResponseEntity<>(userService.AdminUpdateUserById(userId, user), HttpStatus.OK);
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
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/getUserByName")
    public ResponseEntity<User> getUserByName(@RequestParam String userName){
        return new ResponseEntity<>(userService.getUserByName(userName), HttpStatus.OK);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable  int userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId,@RequestBody User user){
        return new ResponseEntity<>(userService.updateUserById(userId, user), HttpStatus.OK);
    }











}
