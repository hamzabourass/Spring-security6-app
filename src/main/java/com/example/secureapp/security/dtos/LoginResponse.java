package com.example.secureapp.security.dtos;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;

    private String refreshToken;

    private long expiresIn;

}
