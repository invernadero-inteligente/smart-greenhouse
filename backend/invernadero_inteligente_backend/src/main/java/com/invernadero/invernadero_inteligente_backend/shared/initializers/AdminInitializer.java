package com.invernadero.invernadero_inteligente_backend.shared.initializers;

import com.invernadero.invernadero_inteligente_backend.modules.users.model.User;
import com.invernadero.invernadero_inteligente_backend.modules.users.model.UserRole;
import com.invernadero.invernadero_inteligente_backend.modules.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String ADMIN_EMAIL = "admin3@invernadero.com";
    private static final String ADMIN_PASSWORD = "Admin123456";
    private static final String ADMIN_NAME = "Administrador del Sistema";

    @Override
    public void run(String... args) throws Exception {
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        try {
            // Verificar si el usuario administrador ya existe
            if (userRepository.existsByEmail(ADMIN_EMAIL)) {
                log.info("Usuario administrador ya existe: {}", ADMIN_EMAIL);
                return;
            }

            // Crear nuevo usuario administrador
            User adminUser = User.builder()
                    .fullName(ADMIN_NAME)
                    .email(ADMIN_EMAIL)
                    .password(passwordEncoder.encode(ADMIN_PASSWORD))
                    .role(UserRole.ADMIN)
                    .active(true)
                    .build();

            userRepository.save(adminUser);
            log.info("✓ Usuario administrador creado exitosamente");
            log.info("  Email: {}", ADMIN_EMAIL);
            log.info("  Contraseña: {}", ADMIN_PASSWORD);
            log.info("  (Cambiar contraseña después del primer login)");

        } catch (Exception e) {
            log.error("Error al crear el usuario administrador: {}", e.getMessage(), e);
        }
    }
}
