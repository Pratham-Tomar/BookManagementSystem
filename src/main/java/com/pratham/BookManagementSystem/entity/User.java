package com.pratham.BookManagementSystem.entity;

import com.pratham.BookManagementSystem.enums.Role;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    @Column(unique = true)
    private String userEmail;
    private String userPassword;

    @Enumerated(EnumType.STRING)   //storing the enum "role" as string in the database
    private Role role;

    public User(){}

    public User(int userId, String userName , String userEmail, String userPassword, Role role){
        this.userId=userId;
        this.userName=userName;
        this.userEmail=userEmail;
        this.userPassword=userPassword;
        this.role=role;
    }

    //getters

    public int getUserId(){
        return  userId;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getUserPassword(){
        return  userPassword;
    }

    public Role getRole(){
        return role;
    }

    //setters

    public void setUserId(int userId){
        this.userId=userId;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public void setUserEmail(String userEmail){
        this.userEmail=userEmail;
    }

    public void setUserPassword(String userPassword){
        this.userPassword=userPassword;
    }

    public void setRole(Role role){
        this.role=role;
    }

}
