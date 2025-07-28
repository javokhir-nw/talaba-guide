package dev.javokhir.talabaguide.config.init;

import dev.javokhir.talabaguide.enums.Status;
import dev.javokhir.talabaguide.models.Authority;
import dev.javokhir.talabaguide.models.Role;
import dev.javokhir.talabaguide.models.User;
import dev.javokhir.talabaguide.repositories.AuthorityRepository;
import dev.javokhir.talabaguide.repositories.RoleRepository;
import dev.javokhir.talabaguide.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Required for encoding admin password

    @Bean
    public CommandLineRunner init(AuthorityRepository authorityRepository) {
        return args -> {

            Role adminRole = roleRepository.findByCode("ROLE_ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ADMIN");
                        role.setCode("ROLE_ADMIN");
                        role.setDescription("INIT ADMIN ROLE");
                        role.setAuthorities(new HashSet<>(authorityRepository.findAll()));
                        return roleRepository.save(role);
                    });

            Optional<User> existingAdmin = userRepository.findByUsername("admin");
            if (existingAdmin.isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setIsEnabled(true);
                admin.setStatus(Status.ACTIVE);
                admin.setRole(adminRole);
                userRepository.save(admin);
            }
        };
    }
}
