package com.example.SpringSecurity.SpringSecurity.controllers;

import com.example.SpringSecurity.SpringSecurity.entities.PostEntity;
import com.example.SpringSecurity.SpringSecurity.entities.UserEntity;
import com.example.SpringSecurity.SpringSecurity.exceptions.ResourceNotFoundException;
import com.example.SpringSecurity.SpringSecurity.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/posts")
    public List<PostEntity> getAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/post/{postId}")
    public PostEntity getPostById(@PathVariable Long postId){

        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("User {}",user);

        Optional<PostEntity> post= postRepository.findById(postId);
        if(post.isPresent()){
            return post.get();
        }
        else{
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    @PostMapping("/post")
    public void createNewPost(@RequestBody PostEntity post){
        postRepository.save(post);
    }
}
