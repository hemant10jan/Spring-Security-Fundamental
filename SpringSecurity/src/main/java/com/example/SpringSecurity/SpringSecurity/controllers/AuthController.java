package com.example.SpringSecurity.SpringSecurity.controllers;

import com.example.SpringSecurity.SpringSecurity.dto.LoginDto;
import com.example.SpringSecurity.SpringSecurity.dto.SignUpDto;
import com.example.SpringSecurity.SpringSecurity.dto.UserDto;
import com.example.SpringSecurity.SpringSecurity.services.AuthService;
import com.example.SpringSecurity.SpringSecurity.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){
        UserDto userDto = authService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> signUp(@RequestBody LoginDto loginDto, HttpServletResponse response){
        String token = authService.login(loginDto);

        Cookie cookie= new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
