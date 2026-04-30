package com.invernadero.invernadero_inteligente_backend.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ConfiguraciÃ³n de seguridad y encriptaciÃ³n
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean para codificaciÃ³n de contraseÃ±as
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

