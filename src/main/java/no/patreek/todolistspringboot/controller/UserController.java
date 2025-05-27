package no.patreek.todolistspringboot.controller;

import no.patreek.todolistspringboot.model.User;
import no.patreek.todolistspringboot.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public static class RegisterRequest {
        public String username;
        public String email;
        public String password;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            User newUser = userService.registerUser(request.username, request.email, request.password);

            // Returner JSON med suksessmelding
            return ResponseEntity.ok(Map.of("message", "Bruker opprettet: " + newUser.getUsername()));
        } catch (Exception e) {
            // Returner JSON med feilmelding og status 400 (Bad Request)
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
