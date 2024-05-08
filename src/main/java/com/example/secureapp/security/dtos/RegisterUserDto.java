package com.example.secureapp.security.dtos;

import com.example.secureapp.security.entities.AppRole;
import lombok.Data;
import java.util.HashSet;

@Data
public class RegisterUserDto {
    private String email;

    private String password;

    private String fullName;
}
