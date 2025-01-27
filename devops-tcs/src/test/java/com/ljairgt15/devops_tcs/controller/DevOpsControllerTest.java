package com.ljairgt15.devops_tcs.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ljairgt15.devops_tcs.configuration.SecurityConfig;
import com.ljairgt15.devops_tcs.models.dto.DevOpsResponseDTO;
import com.ljairgt15.devops_tcs.util.JWTGenerator;
import com.ljairgt15.devops_tcs.util.JWTTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DevOpsController.class)
@Import(SecurityConfig.class)
class DevOpsControllerTest {
    //Inyectar componente
    // con MockMvc puedo simular peticiones
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JWTGenerator jwtGenerator; // Esto crea un mock del bean JWTGenerator
    @MockBean
    private JWTTokenProvider jwtTokenProvider;

    String generateMockToken() {
        return JWT.create()
                .withSubject("TestUser")
                .withIssuer("MockIssuer")
                // No incluir withExpiresAt
                .sign(Algorithm.HMAC256("secret-key"));
    }




    @BeforeEach
    void setUp() {
        when(jwtGenerator.generateToken()).thenReturn(generateMockToken());
        when(jwtTokenProvider.getSubject(anyString())).thenReturn("expected-username");
        when(jwtTokenProvider.isTokenValid(anyString(),anyString())).thenReturn(true);
    }
    @Test
    public void getPostWithoutHeaders() throws Exception {
        /*
        DevOpsRequestDTO devops = new DevOpsRequestDTO ();
        devops.setMessage("This is a test");
        devops.setTo("Juan Perez");
        devops.setFrom("Rita Asturia");
        devops.setTimeToLifeSec(45);

         */
        mockMvc.perform(post("/DevOps").contentType(MediaType.APPLICATION_JSON).
                        content("{\n"+
                 "       \"message\": \"This is a test\", \n"+
                 "       \"to\": \"Juan Perez\", \n"+
                 "       \"from\": \"Rita Asturia\", \n"+
                 "       \"timeToLifeSec\": 45\n"+
                                "}")
                ).andExpect(status().isOk());
    }
    @Test
    public void testPostWithHeaders() throws Exception {
        // Prueba cuando los encabezados están presentes
        mockMvc.perform(post("/DevOps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Parse-REST-API-Key", "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c")
                        .header("X-JWT-KWY", jwtGenerator.generateToken())
                        .content("{\n" +
                                "       \"message\": \"This is a test\", \n" +
                                "       \"to\": \"Juan Perez\", \n" +
                                "       \"from\": \"Rita Asturia\", \n" +
                                "       \"timeToLifeSec\": 45\n" +
                                "}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Juan Perez your message will be sent"));
    }

    @Test
    public void testPostWithInvalidApiKey() throws Exception {
        // Prueba con una API Key inválida
        mockMvc.perform(post("/DevOps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Parse-REST-API-Key", "invalid/api/key")
                        .header("X-JWT-KWY", jwtGenerator.generateToken())
                        .content("{\n" +
                                "       \"message\": \"This is a test\", \n" +
                                "       \"to\": \"Juan Perez\", \n" +
                                "       \"from\": \"Rita Asturia\", \n" +
                                "       \"timeToLifeSec\": 45\n" +
                                "}")
                )
                .andExpect(status().isOk());

    }

    @Test
    public void testPostWithInvalidToken() throws Exception {
        // Configura mock para devolver false en la validación del token
        when(jwtTokenProvider.isTokenValid(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/DevOps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Parse-REST-API-Key", "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c")
                        .header("X-JWT-KWY", "Bearer invalid-token")
                        .content("{\n" +
                                "       \"message\": \"This is a test\", \n" +
                                "       \"to\": \"Juan Perez\", \n" +
                                "       \"from\": \"Rita Asturia\", \n" +
                                "       \"timeToLifeSec\": 45\n" +
                                "}")
                )
                .andExpect(status().isOk());

    }
}