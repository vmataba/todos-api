package com.tabaapps.todos.commands;

import com.tabaapps.todos.security.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Dummy implements CommandLineRunner {

    @Autowired
    private AuthToken token;

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(this.token.privateKey.getFormat());
    }
}
