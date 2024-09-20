package com.ljairgt15.devops_tcs.configuration;


import com.ljairgt15.devops_tcs.Filter.JwtAndApiKeyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAndApiKeyFilter jwtAndApiKeyFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Deshabilitar CSRF solo para simplificar las pruebas
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/DevOps").permitAll()  // Permitir acceso a /DevOps
                                .anyRequest().authenticated()  // Requerir autenticación para cualquier otro endpoint
                )
                .addFilterBefore(jwtAndApiKeyFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

