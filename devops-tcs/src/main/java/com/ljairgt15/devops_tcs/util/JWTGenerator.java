package com.ljairgt15.devops_tcs.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {

        private static final String SECRET = "09c53c457e150766aacce02ca27ec8929797b4a3b1093d5bc6aabc0f4b4c307d";
        public String generateToken() {
            // 1 hora de validez
            return JWT.create()
                    .withSubject("Jair Gomez")
                    .withIssuer("TCS.devopsazure")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 hora de validez
                    .sign(Algorithm.HMAC256(SECRET));
        }

}
