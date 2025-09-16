//package org.example;
//import org.example.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class DebugRunner {
//
//    @Bean
//    CommandLineRunner checkPassword(UserRepository userRepository, PasswordEncoder encoder) {
//        return args -> {
//            userRepository.findAll().forEach(u -> {
//                System.out.println(">>> User: " + u.getUsername());
//                System.out.println(">>> Encoded password: " + u.getPassword());
//                boolean matches = encoder.matches("твойПароль", u.getPassword());
//                System.out.println(">>> Проверка пароля 'твойПароль' -> " + matches);
//            });
//        };
//    }
//}
