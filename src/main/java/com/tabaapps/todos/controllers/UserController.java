package com.tabaapps.todos.controllers;

import com.tabaapps.todos.models.User;
import com.tabaapps.todos.repositories.UserRepository;
import com.tabaapps.todos.security.ResponseException;
import com.tabaapps.todos.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Security security;


    @GetMapping
    public List<User> viewAll() {
        return userRepository.findAll();
    }


    @PostMapping
    public User signup(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PutMapping(path = "/{id}")
    public User update(@RequestBody User user, @PathVariable("id") Long id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new Exception(String.format("User with id %s does not exist", id));
        }
        User savedUser = optionalUser.get();
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setEmail(user.getEmail());
        return this.userRepository.save(savedUser);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String credentials) throws ResponseException {
        byte[] credentialsBytes = Base64.getDecoder().decode(credentials.replace("Basic ", ""));
        String textCredentials = new String(credentialsBytes);
        String[] textCredentialsBundle = textCredentials.split(":");
        User user = userRepository.findByEmail(textCredentialsBundle[0]).orElseThrow(() -> new ResponseException("Incorrect user name or password", HttpStatus.BAD_REQUEST));
        if (!security.getPasswordEncoder().matches(textCredentialsBundle[1], user.getPassword())) {
            throw new ResponseException("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }
}
