package com.example.SpringSecurity.SpringSecurity.services;

import com.example.SpringSecurity.SpringSecurity.dto.LoginDto;
import com.example.SpringSecurity.SpringSecurity.dto.SignUpDto;
import com.example.SpringSecurity.SpringSecurity.dto.UserDto;
import com.example.SpringSecurity.SpringSecurity.entities.UserEntity;
import com.example.SpringSecurity.SpringSecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UserDto signUp(SignUpDto signUpDto) {
        Optional<UserEntity> user=userRepository.findByEmail(signUpDto.getEmail());

        if(user.isPresent()){
            throw new BadCredentialsException("User with email already exists."+ signUpDto.getEmail());
        }

        UserEntity toCreate=modelMapper.map(signUpDto,UserEntity.class);
        toCreate.setPassword(passwordEncoder.encode(toCreate.getPassword()));

        UserEntity savedUser=userRepository.save(toCreate);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        UserEntity userEntity= (UserEntity) authentication.getPrincipal();
        String token=jwtService.generateToken(userEntity);

        return token;
    }
}
