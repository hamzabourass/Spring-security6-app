package com.example.secureapp.security.service;

import com.example.secureapp.security.dtos.LoginUserDto;
import com.example.secureapp.security.dtos.RegisterUserDto;
import com.example.secureapp.security.dtos.TokenDTO;
import com.example.secureapp.security.entities.AppRole;
import com.example.secureapp.security.entities.AppUser;
import com.example.secureapp.security.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AuthenticationService {
    private final AppUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    public AppUser signup(RegisterUserDto input) {
        AppUser user = new AppUser();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public AppUser authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
    public TokenDTO refresh(String refreshToken) {
        refreshToken = refreshToken.substring(7);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setRefreshToken(refreshToken);
        tokenDTO.setAccessToken(jwtService.refreshToken(refreshToken));
        return tokenDTO;
    }
}
