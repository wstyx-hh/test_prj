package org.example;

import lombok.RequiredArgsConstructor;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitializerUser implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        userRepository.findByUsername("admin")
                .orElseGet(() -> {
                    User admin = User.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin"))
                            .role(Role.ADMIN)
                            .active(true)
                            .build();
                    return userRepository.save(admin);
                });

        userRepository.findByUsername("HR")
                .orElseGet(() -> {
                    User user = User.builder()
                            .username("HR")
                            .password(passwordEncoder.encode("HR"))
                            .role(Role.HR)
                            .active(true)
                            .build();
                    return userRepository.save(user);
                });
    }
}
