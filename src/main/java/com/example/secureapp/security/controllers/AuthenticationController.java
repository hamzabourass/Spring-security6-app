package com.example.secureapp.security.controllers;

import com.example.secureapp.security.dtos.LoginResponse;
import com.example.secureapp.security.dtos.LoginUserDto;
import com.example.secureapp.security.dtos.RegisterUserDto;
import com.example.secureapp.security.dtos.TokenDTO;
import com.example.secureapp.security.entities.AppRole;
import com.example.secureapp.security.entities.AppUser;
import com.example.secureapp.security.service.AccountService;
import com.example.secureapp.security.service.AuthenticationService;
import com.example.secureapp.security.service.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final AccountService accountService;


    @PostMapping("/signup")
    public ResponseEntity<AppUser> register( @Valid @RequestBody RegisterUserDto registerUserDto) {
        AppUser registeredUser = authenticationService.signup(registerUserDto);
        accountService.addRoleToUser(registeredUser.getEmail(),"USER");

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUserDto loginUserDto) {
        AppUser authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        String refreshToken = jwtService.refreshToken(jwtToken);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(jwtToken);
        loginResponse.setRefreshToken(refreshToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    @GetMapping("/refresh")
    public ResponseEntity<TokenDTO> refresh(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        return new ResponseEntity<>(authenticationService.refresh(refreshToken), HttpStatus.OK);
    }
}
