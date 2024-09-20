package com.ljairgt15.devops_tcs.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JWTGenerator {

    public static void main(String[] args) {
        String secret = "09c53c457e150766aacce02ca27ec8929797b4a3b1093d5bc6aabc0f4b4c307d";
        String token = JWT.create()
                .withSubject("Jair Gomez")
                .withIssuer("TCS.devopsazure")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 hora de validez
                .sign(Algorithm.HMAC256(secret));

        System.out.println("JWT Token: " + token);
    }
}