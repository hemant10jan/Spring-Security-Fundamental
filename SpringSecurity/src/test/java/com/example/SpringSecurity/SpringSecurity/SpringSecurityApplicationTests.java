package com.example.SpringSecurity.SpringSecurity;

import com.example.SpringSecurity.SpringSecurity.entities.UserEntity;
import com.example.SpringSecurity.SpringSecurity.repositories.UserRepository;
import com.example.SpringSecurity.SpringSecurity.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

//	@Test
//	void contextLoads() {
//        UserEntity user=new UserEntity(4L,"Rajan","bkjbkj","1234");
//
//        String token=jwtService.generateToken(user);
//        System.out.println(token);
//
//        Long id= jwtService.getUserIdFromToken(token);
//        System.out.println(id);
//	}

//    @Test
//    void createUser(){
//        UserEntity user=new UserEntity();
//        user.setEmail("hema");
//        user.setPassword("mvlj");
//
//        userRepository.save(user);
//    }

}
