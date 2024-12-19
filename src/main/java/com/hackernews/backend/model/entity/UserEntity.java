package com.hackernews.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // Mot de passe en clair si vous insistez

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
}



