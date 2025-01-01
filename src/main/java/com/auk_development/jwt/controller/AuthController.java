package com.auk_development.jwt.controller;

import com.auk_development.jwt.entity.UserEntity;
import com.auk_development.jwt.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return authService.getAllUsers();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user){
        return authService.createUser(user);
    }

}
