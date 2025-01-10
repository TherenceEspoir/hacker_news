package com.hackernews.backend.services.implementations;

import com.hackernews.backend.exceptions.UserNotFoundException;
import com.hackernews.backend.exceptions.UsernameAlreadyExistsException;
import com.hackernews.backend.model.dao.postgres.UserRepository;
import com.hackernews.backend.model.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param username 
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities("USER")        // Obligatoire même si pas de rôles
                        .build()
                )
                .orElseThrow(() -> new UserNotFoundException(username));
    }


    public UserEntity createUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password); // En clair pour l'instant, j'utiliserai un encodeur à terme

        return userRepository.save(user);
    }

    public boolean validateCredentials(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        return userDetails != null && userDetails.getPassword().equals(password);
    }


    public static void main(String[] args) {
        Base64.Encoder encoder = Base64.getEncoder();
        System.out.println(encoder.encodeToString("tony:tony".getBytes()));
    }
}
