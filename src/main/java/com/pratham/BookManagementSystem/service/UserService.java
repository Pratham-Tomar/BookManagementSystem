package com.pratham.BookManagementSystem.service;

import com.pratham.BookManagementSystem.dtos.BookDto;
import com.pratham.BookManagementSystem.dtos.UserDto;
import com.pratham.BookManagementSystem.entity.Book;
import com.pratham.BookManagementSystem.entity.User;
import com.pratham.BookManagementSystem.exception.UsernameNotFoundException;
import com.pratham.BookManagementSystem.mapper.BookMapper;
import com.pratham.BookManagementSystem.mapper.UserMapper;
import com.pratham.BookManagementSystem.repository.UserRepository;
import org.slf4j.LoggerFactory;
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

    private static final org.slf4j.Logger log= LoggerFactory.getLogger(UserService.class);

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> AdmingetAllUsers(){
        log.info("Fetching all users");
        List<User> users= userRepository.findAll();
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());

    }
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto AdmingetUserById(int userId){
        log.info("Fetching user with ID: {}", userId);
        User user= userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId: ", userId));
        log.debug("Fetched user details: {}", user);
        return UserMapper.toDto(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserDto AdminAddUser(UserDto userDto){
        User user=UserMapper.toEntity(userDto);
        log.info("Adding a new user: {}", user.getUsername());
         User savedUser=userRepository.save(user);
        log.info("User added successfully with");
         return UserMapper.toDto(savedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserDto AdminUpdateUserById(int userId , UserDto userDto){
        User user=UserMapper.toEntity(userDto);
        log.info("Updating the user with userId: {} and UserDto: {}", userId, userDto);
        User userExists = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId",userId));

        userExists.setUsername(user.getUsername());
        userExists.setAddress(user.getAddress());
        userExists.setMobileNumber(user.getMobileNumber());
        userExists.setRegistrationNumber(user.getRegistrationNumber());
        userExists.setEmail(user.getEmail());
        User savedUser= userRepository.save(userExists);
        log.info("User updated successfully with ID: {}", savedUser.getId());
        return UserMapper.toDto(savedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void AdminDeleteUserById(int userId){
        log.info("Deleting user with ID: {}", userId);
        User userExist = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("username",userId));
        log.info("User deleted successfully with ID: {}", userId);
        userRepository.delete(userExist);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void AdminDeleteUserByName(String userName){
        log.info("Deleting user with username: {}", userName);
        User userExist = userRepository.findByUsername(userName).orElseThrow(()-> new UsernameNotFoundException("userName",userName));
        log.info("User deleted successfully with username: {}", userName);
        userRepository.delete(userExist);
    }

    public UserDto registerUser(UserDto userDto){
        User user =UserMapper.toEntity(userDto);
        log.info("Registering a new user: {}", user.getUsername());
        User savedUser= userRepository.save(user);
        log.info("User registered successfully with ID: {}", savedUser.getId());
        return UserMapper.toDto(savedUser);
    }

    public UserDto getUserByName(String username){
        log.info("Fetching user with username: {}", username);
        User user=userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username: ", username));
        log.debug("Fetched user details in getUserByName : {}", user);
         return UserMapper.toDto(user);
    }

    public UserDto getUserById(int userId){
        log.info("Fetching user in getUserById with ID: {}", userId);
        User user= userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId: ", userId));
        log.debug("Fetched the  user details: {}", user);
        return UserMapper.toDto(user);
    }

    public UserDto updateUserById(int userId , UserDto userDto){
        User user=UserMapper.toEntity(userDto);
        log.info("Updating  user with userId: {} and UserDto: {}", userId, userDto);
        User userExists = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId",userId));

        userExists.setUsername(user.getUsername());
        userExists.setAddress(user.getAddress());
        userExists.setMobileNumber(user.getMobileNumber());
        userExists.setRegistrationNumber(user.getRegistrationNumber());
        userExists.setEmail(user.getEmail());
        User savedUser= userRepository.save(userExists);
        log.info("User updated successfully with UserID: {}", savedUser.getId());
        return UserMapper.toDto(savedUser);
    }

    public List<BookDto> getAllBooks() {
        log.info("Fetching all books");
         List<BookDto> booksDto=  bookService.getAllBookdetails();
        log.info("Fetched all books");
         return booksDto;

    }

}
