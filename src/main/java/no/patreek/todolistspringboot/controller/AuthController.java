package no.patreek.todolistspringboot.controller;

import no.patreek.todolistspringboot.model.User;
import no.patreek.todolistspringboot.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public static class RegisterRequest {
        public String username;
        public String password;
        public String email;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User newUser = userService.registerUser(request.username, request.email, request.password);
            return ResponseEntity.ok("Bruker opprettet: " + newUser.getUsername());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Spring Security håndterer login automatisk via /login
    // Ingen behov for egen login-endpoint
}