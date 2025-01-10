package com.hackernews.backend.controllers;


import com.clickhouse.client.internal.apache.hc.client5.http.utils.Base64;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hackernews.backend.exceptions.UsernameAlreadyExistsException;
import com.hackernews.backend.model.entity.UserEntity;
import com.hackernews.backend.services.implementations.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/news/users")
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String authHeader) {
        // Décode l'en-tête "Authorization" pour récupérer le nom d'utilisateur et le mot de passe
        String[] credentials = new String(Base64.decodeBase64(authHeader.split(" ")[1])).split(":");
        String username = credentials[0];
        String password = credentials[1];

        // Vérifie les identifiants (par exemple, en les comparant à ceux stockés dans la base de données)
        if (userService.validateCredentials(username, password)) {
            // Renvoie une réponse positive (par exemple, un jeton d'authentification)
            return ResponseEntity.ok().build();
        } else {
            // Renvoie une erreur
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
