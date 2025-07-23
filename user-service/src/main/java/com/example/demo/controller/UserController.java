package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor  
public class UserController {

	private final UserService userService;
	private final UserRepository userRepository; 

    // DTO class for login request
    public static class LoginRequest {
        public String email;
        public String password;
    }

    // Register user endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User savedUser = userService.register(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        try {
            String token = userService.login(request.email, request.password);
            return ResponseEntity.ok().body(new TokenResponse(token));
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
        
        
    }
    
 // Only accessible by RESTAURANT users
    @GetMapping("/restaurant-dashboard")
    @PreAuthorize("hasRole('RESTAURANT')")
    public ResponseEntity<String> restaurantDashboard() {
        return ResponseEntity.ok("Welcome, Restaurant Owner!");
    }
    
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {
        return ResponseEntity.ok("You are authenticated, welcome!");
    }
    
    @GetMapping("/api/users/email")
    public String getNameByEmail(@RequestParam String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return user.getName();
    }



 // Only accessible by CUSTOMER users
 @GetMapping("/customer-dashboard")
 @PreAuthorize("hasRole('CUSTOMER')")
 public ResponseEntity<String> customerDashboard() {
     return ResponseEntity.ok("Welcome, Customer!");
 }
 
    // Response class for token
    @lombok.Data
    @lombok.AllArgsConstructor
    static class TokenResponse {
        private String token;
    }
}
