package com.tabaapps.todos.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tabaapps.todos.security.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

@RestController
@RequestMapping(path = "/dummy")
public class DummyController {

    @Autowired
    private AuthToken authToken;

    @GetMapping("")
    public String renderIndexPage(){
        return "Dummy Controller works";
    }

    @GetMapping("/public-key")
    public ResponseEntity<?> loadPublicKey(){
       return ResponseEntity.ok(new String(authToken.rsaPublicKey.getEncoded()));
    }

    @GetMapping("/jwt-token")
    public ResponseEntity<?> loadJwtToken(){
        return ResponseEntity.ok(authToken.generateToken());
    }

    @GetMapping("/verify-jwt-token")
    public ResponseEntity<?> verifyJwtToken(){
        String token = authToken.generateToken().concat("*");
        return ResponseEntity.ok(authToken.verify(token));
    }

    @GetMapping("/say")
    public List<Integer> saySomething(){
        return List.of(1,2,3,4);
    }
}
