package com.example.test;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class SecretKeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        // Encode the key in Base64 format
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println("Your Secret Key: " + base64Key);
    }
}
