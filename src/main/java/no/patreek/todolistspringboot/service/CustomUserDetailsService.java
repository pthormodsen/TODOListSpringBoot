package no.patreek.todolistspringboot.service;

import no.patreek.todolistspringboot.model.User;
import no.patreek.todolistspringboot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Forsøker å finne bruker: " + username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("Bruker ikke funnet: " + username);
                    return new UsernameNotFoundException("Bruker ikke funnet: " + username);
                });

        System.out.println("Bruker funnet: " + user.getUsername() + ", enabled: " + user.isEnabled());
        System.out.println("Password hash i database: " + user.getPasswordHash());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .authorities("ROLE_USER") // Endret fra tom liste til faktisk rolle
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!user.isEnabled())
                .build();
    }
}