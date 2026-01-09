package com.gamechane.backend.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.gamechane.backend.security.user.domain.Role;
import com.gamechane.backend.security.user.domain.User;
import com.gamechane.backend.security.user.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "*2#!4a5N7ki@K%Ky&4Vt";
    private final Role ADMIN_ROLE = Role.ADMIN;

    public DatabaseInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        // Controleer of er al gebruikers zijn, als er geen gebruikers zijn, maak dan een ADMIN-gebruiker aan
        // TODO: implement a better default credentials system
        // if (userRepository.count() == 0) {
        if (userRepository.findByUsername(ADMIN_USERNAME).isPresent())
            return;
        String ADMIN_EMAIL_ADDRESS = "admin@example.com";
        User admin = new User(ADMIN_USERNAME, ADMIN_EMAIL_ADDRESS, passwordEncoder.encode(ADMIN_PASSWORD));
        admin.addRole(ADMIN_ROLE);

        userRepository.save(admin);
        // }
    }

    @PreDestroy
    public void cleanup() {
        // Verwijder de admin-gebruiker bij het afsluiten van de applicatie
        userRepository.findByUsername(ADMIN_USERNAME).ifPresent(userRepository::delete);
    }
}
