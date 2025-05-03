package com.pratham.BookManagementSystem.service;

import com.pratham.BookManagementSystem.dtos.BookDto;
import com.pratham.BookManagementSystem.dtos.UserDto;
import com.pratham.BookManagementSystem.entity.Book;
import com.pratham.BookManagementSystem.entity.User;
import com.pratham.BookManagementSystem.exception.UsernameNotFoundException;
import com.pratham.BookManagementSystem.mapper.BookMapper;
import com.pratham.BookManagementSystem.mapper.UserMapper;
import com.pratham.BookManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> AdmingetAllUsers(){
        List<User> users= userRepository.findAll();
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());

    }
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto AdmingetUserById(int userId){
        User user= userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId: ", userId));
        return UserMapper.toDto(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserDto AdminAddUser(UserDto userDto){
        User user=UserMapper.toEntity(userDto);
         User savedUser=userRepository.save(user);
         return UserMapper.toDto(savedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserDto AdminUpdateUserById(int userId , UserDto userDto){
        User user=UserMapper.toEntity(userDto);
        User userExists = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId",userId));

        userExists.setUsername(user.getUsername());
        userExists.setAddress(user.getAddress());
        userExists.setMobileNumber(user.getMobileNumber());
        userExists.setRegistrationNumber(user.getRegistrationNumber());
        User savedUser= userRepository.save(userExists);
        return UserMapper.toDto(savedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void AdminDeleteUserById(int userId){
        User userExist = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("username",userId));
        userRepository.delete(userExist);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void AdminDeleteUserByName(String userName){
        User userExist = userRepository.findByUsername(userName).orElseThrow(()-> new UsernameNotFoundException("userName",userName));
        userRepository.delete(userExist);
    }

    public UserDto registerUser(UserDto userDto){
        User user =UserMapper.toEntity(userDto);
        User savedUser= userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    public UserDto getUserByName(String username){
         User user=userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username: ", username));
         return UserMapper.toDto(user);
    }

    public UserDto getUserById(int userId){
        User user= userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId: ", userId));
        return UserMapper.toDto(user);
    }

    public UserDto updateUserById(int userId , UserDto userDto){
        User user=UserMapper.toEntity(userDto);
        User userExists = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId",userId));

        userExists.setUsername(user.getUsername());
        userExists.setAddress(user.getAddress());
        userExists.setMobileNumber(user.getMobileNumber());
        userExists.setRegistrationNumber(user.getRegistrationNumber());
        User savedUser= userRepository.save(userExists);
        return UserMapper.toDto(savedUser);
    }

    public List<BookDto> getAllBooks() {
         List<BookDto> booksDto=  bookService.getAllBookdetails();
         return booksDto;

    }

}
