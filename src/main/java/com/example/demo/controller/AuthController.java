package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Validate input
        ValidationUtils.validateEmail(user.getEmail());
        ValidationUtils.validatePassword(user.getPassword());
        ValidationUtils.validateNotEmpty(user.getRole(), "Role");
        
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        ValidationUtils.validateNotNull(id, "User ID");
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        ValidationUtils.validateEmail(email);
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User loginRequest) {
        ValidationUtils.validateEmail(loginRequest.getEmail());
        ValidationUtils.validateNotEmpty(loginRequest.getPassword(), "Password");
        
        User user = userService.findByEmail(loginRequest.getEmail());
        AuthResponse response = new AuthResponse("jwt-token", user.getId(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(response);
    }
}