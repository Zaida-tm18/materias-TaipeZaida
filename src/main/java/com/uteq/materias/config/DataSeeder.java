package com.uteq.materias.config;

import com.uteq.materias.model.Usuario;
import com.uteq.materias.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Paso 3 / 4.3 del enunciado: el usuario admin debe existir al arrancar la
// aplicacion desde una BD vacia (migracion + seed automaticos), con la
// contrasena Admin*2026 almacenada como hash BCrypt (nunca texto plano).
@Component
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPasswordHash(passwordEncoder.encode("Admin*2026"));
            admin.setRol("ADMIN");
            usuarioRepository.save(admin);
            System.out.println(">> Usuario semilla 'admin' creado con hash BCrypt.");
        }
    }
}
