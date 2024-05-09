package com.example.secureapp.security.dtos;

import lombok.Data;

@Data
public class TokenDTO {
    String accessToken;
    String refreshToken;
}
