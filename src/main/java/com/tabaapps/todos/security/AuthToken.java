package com.tabaapps.todos.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Service
public class AuthToken {

    static final String ALGORITHM = "RSA";
    static final String ISSUER = "auth0";

    static final int KEY_SIZE = 2048;

    public PrivateKey privateKey;

    public PublicKey rsaPublicKey;

    public AuthToken() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.rsaPublicKey = keyPair.getPublic();
    }

    private Algorithm getJwtAlgorithm() {
        return Algorithm.RSA256((RSAPublicKey) this.rsaPublicKey, (RSAPrivateKey) this.privateKey);
    }

    public String generateToken() {
        return JWT.create()
                .withIssuer(ISSUER)
                .sign(this.getJwtAlgorithm());
    }

    public boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(this.getJwtAlgorithm())
                    .withIssuer(ISSUER)
                    .build();
            return verifier.verify(token) != null;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }
}
