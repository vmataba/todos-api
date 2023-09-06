package com.tabaapps.todos.commands;

import com.tabaapps.todos.models.User;
import com.tabaapps.todos.repositories.UserRepository;
import com.tabaapps.todos.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Security security;

    @Override
    public void run(String... args) throws Exception {
        // this.addUsers();
        User victor = userRepository.findByEmail("vmataba0@gmail.com").orElseThrow(() -> new Exception("User is not found"));
        victor.setPassword(security.getPasswordEncoder().encode("password"));
        User savedUser = this.userRepository.save(victor);
        System.out.println(savedUser.getPassword());
        System.out.println("Password has been successfully updated!");

    }

    private void addUsers() {

    }

}
