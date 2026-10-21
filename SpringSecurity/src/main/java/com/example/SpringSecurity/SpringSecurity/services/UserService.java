package com.example.SpringSecurity.SpringSecurity.services;


import com.example.SpringSecurity.SpringSecurity.dto.LoginDto;
import com.example.SpringSecurity.SpringSecurity.dto.SignUpDto;
import com.example.SpringSecurity.SpringSecurity.dto.UserDto;
import com.example.SpringSecurity.SpringSecurity.entities.UserEntity;
import com.example.SpringSecurity.SpringSecurity.exceptions.ResourceNotFoundException;
import com.example.SpringSecurity.SpringSecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()->new BadCredentialsException("Resource not found."));
    }

    public UserEntity getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource not found."));
    }
}
