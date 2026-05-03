-- V4__insert_admin_user.sql

-- Insertar usuario administrador inicial
INSERT INTO users (email, password, role, active, full_name, created_at, updated_at)
VALUES (
    'admin@invernadero.com',
    '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', -- password: admin123
    'ADMIN',
    true,
    'Administrador del Sistema',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);