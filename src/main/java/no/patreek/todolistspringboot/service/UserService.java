package no.patreek.todolistspringboot.service;

import no.patreek.todolistspringboot.model.User;
import no.patreek.todolistspringboot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String email, String rawPassword) throws Exception {
        // Sjekk om brukernavn er tatt
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new Exception("Brukernavn er allerede tatt");
        }

        // Sjekk om email er tatt
        Optional<User> existingEmail = userRepository.findByEmail(email);
        if (existingEmail.isPresent()) {
            throw new Exception("Epost er allerede i bruk");
        }

        // Opprett ny bruker
        String hashedPassword = passwordEncoder.encode(rawPassword);
        User user = new User(username, email, hashedPassword);
        return userRepository.save(user);
    }

    // Spring Security håndterer login via CustomUserDetailsService
    // Ingen behov for egen login-metode
}