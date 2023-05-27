package com.tabaapps.todos.controllers;

import com.tabaapps.todos.models.Label;
import com.tabaapps.todos.models.User;
import com.tabaapps.todos.repositories.LabelRepository;
import com.tabaapps.todos.repositories.UserRepository;
import com.tabaapps.todos.security.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/labels")
public class LabelController {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/{userId}")
    public List<Label> viewLabels(@PathVariable Long userId) throws Exception {
         Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ResponseException("User is not found", HttpStatus.NOT_FOUND);
        }
        try {
            User user = optionalUser.get();
            return labelRepository.findByUser(user);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/{userId}")
    public Label addNewLabel(@PathVariable Long userId, @RequestBody Label label) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ResponseException("User is not found", HttpStatus.BAD_REQUEST);
        }
        try {
            label.setUser(optionalUser.get());
            return labelRepository.save(label);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public Label updateLabel(@PathVariable Long id, @RequestBody Label label) throws Exception {
        Optional<Label> optionalLabel = labelRepository.findById(id);
        if (optionalLabel.isEmpty()) {
            throw new ResponseException("Label is not found", HttpStatus.NOT_FOUND);
        }
        try {
            Label savedLabel = optionalLabel.get();
            savedLabel.setName(label.getName());
            return labelRepository.save(savedLabel);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteLabel(@PathVariable Long id) throws Exception {
        Optional<Label> optionalLabel = labelRepository.findById(id);
        if (optionalLabel.isEmpty()) {
            throw new ResponseException("Label is not found", HttpStatus.NOT_FOUND);
        }
        Label savedLabel = optionalLabel.get();
        labelRepository.delete(savedLabel);
        return ResponseEntity.ok(id);
    }

}

