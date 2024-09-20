package com.ljairgt15.devops_tcs.Filter;

import com.ljairgt15.devops_tcs.util.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAndApiKeyFilter extends OncePerRequestFilter {

    private final JWTTokenProvider jwtTokenProvider;

    @Value("${security.api.key}")
    private String validApiKey;

    public JwtAndApiKeyFilter(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Validar si la solicitud es OPTIONS
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // Obtener API Key del encabezado
        String apiKeyHeader = request.getHeader("X-Parse-REST-API-Key");

        // Obtener JWT del encabezado
        String jwtHeader = request.getHeader("X-JWT-KWY");

        // Validar API Key
        if (apiKeyHeader == null || !apiKeyHeader.equals(validApiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid API Key");
            return;
        }

        // Validar JWT
        if (jwtHeader != null) {
            String token = jwtHeader;

            // Extraer el sujeto (username) del token
            String username = jwtTokenProvider.getSubject(token);

            // Verificar si el token es válido
            if (jwtTokenProvider.isTokenValid(username, token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                Authentication authentication = jwtTokenProvider.getAuthentication(username, token, request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT");
                return;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("JWT missing");
            return;
        }

        // Continuar con el procesamiento del filtro
        filterChain.doFilter(request, response);
    }
}
