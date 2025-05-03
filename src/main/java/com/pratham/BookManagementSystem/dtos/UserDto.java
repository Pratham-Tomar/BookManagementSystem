package com.pratham.BookManagementSystem.dtos;

import com.pratham.BookManagementSystem.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    private String username;
    private String password;
    private String mobileNumber;
    private String Address;
    private String registrationNumber;
    private Role role;
}
