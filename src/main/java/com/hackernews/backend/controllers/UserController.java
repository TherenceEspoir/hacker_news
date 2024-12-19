package com.hackernews.backend.controllers;


import com.hackernews.backend.model.entity.UserEntity;
import com.hackernews.backend.services.implementations.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestParam String username, @RequestParam String password) {
        UserEntity newUser = userService.createUser(username, password);
        return ResponseEntity.ok(newUser);
    }

}
