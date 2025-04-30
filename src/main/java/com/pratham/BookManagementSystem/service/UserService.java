package com.pratham.BookManagementSystem.service;

import com.pratham.BookManagementSystem.entity.User;
import com.pratham.BookManagementSystem.exception.UsernameNotFoundException;
import com.pratham.BookManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<User> AdmingetAllUsers(){
        return userRepository.findAll();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public User AdmingetUserById(int userId){
        return userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId: ", userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User AdminAddUser(User user){
        Optional<User> userExists = userRepository.findByUsername(user.getUsername());
        if(userExists.isPresent()){
            throw new UsernameNotFoundException("userName",user.getUsername());
        }
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User AdminUpdateUserById(int userId , User user){
        User userExists = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId",userId));

        userExists.setUsername(user.getUsername());
        userExists.setAddress(user.getAddress());
        userExists.setMobileNumber(user.getMobileNumber());
        userExists.setRegistrationNumber(user.getRegistrationNumber());
        return userRepository.save(userExists);
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

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public User getUserByName(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username: ", username));
    }

    public User getUserById(int userId){
        return userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId: ", userId));
    }

    public User updateUserById(int userId , User user){
        User userExists = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("userId",userId));

        userExists.setUsername(user.getUsername());
        userExists.setAddress(user.getAddress());
        userExists.setMobileNumber(user.getMobileNumber());
        userExists.setRegistrationNumber(user.getRegistrationNumber());
        return userRepository.save(userExists);
    }

}
