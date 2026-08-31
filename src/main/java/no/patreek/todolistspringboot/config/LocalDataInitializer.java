package no.patreek.todolistspringboot.config;

import no.patreek.todolistspringboot.model.User;
import no.patreek.todolistspringboot.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class LocalDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LocalDataInitializer(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("testtest").isEmpty()) {

            User user = new User(
                "testtest",
                "testtest@test.com",
                passwordEncoder.encode("testtest")
            );

            userRepository.save(user);

            System.out.println("Local test user created");
        }
    }
}