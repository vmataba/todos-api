package com.tabaapps.todos.security;

import com.tabaapps.todos.models.User;
import com.tabaapps.todos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Incorrect username or password"));
        return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(user.getPassword())
                .build();
    }
}
