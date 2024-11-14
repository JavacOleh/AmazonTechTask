package me.amazon.controller;

import me.amazon.model.User;
import me.amazon.security.JwtResponse;
import me.amazon.security.JwtTokenProvider;
import me.amazon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password) {
        if (userRepository.findUserByUserName(username) != null) {
            return ResponseEntity.status(400).body("User already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);

        // Генерируем токен
        String token = jwtTokenProvider.generateToken(username);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        String login = new StringBuilder(username).substring(0, username.lastIndexOf(','));
        String pass = new StringBuilder(password).substring(0, password.lastIndexOf(','));

        User user = userRepository.findUserByUserName(login);

        if (user != null && user.getPassword().equals(pass)) {
            String token = jwtTokenProvider.generateToken(login);
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
