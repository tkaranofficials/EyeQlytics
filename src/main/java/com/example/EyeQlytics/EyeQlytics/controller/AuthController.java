package com.example.EyeQlytics.EyeQlytics.controller;

import com.example.EyeQlytics.EyeQlytics.dto.ResponseWrapper;
import com.example.EyeQlytics.EyeQlytics.dto.UserLoginResponse;
import com.example.EyeQlytics.EyeQlytics.entity.User;
import com.example.EyeQlytics.EyeQlytics.exception.InvalidCredentialsException;
import com.example.EyeQlytics.EyeQlytics.security.JwtUtil;
import com.example.EyeQlytics.EyeQlytics.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<User>> register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(true, HttpStatus.CREATED, "User registered successfully", registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<UserLoginResponse>> login(@RequestBody Map<String, String> request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.get("email"), request.get("password"))
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid email or password.");
        }

        Optional<User> user = userService.findByEmail(request.get("email"));
        String token = jwtUtil.generateToken(user.get().getEmail());

        UserLoginResponse response = new UserLoginResponse(user.get(), token);
        return ResponseEntity.ok(new ResponseWrapper<>(true, HttpStatus.OK, "Login successful", response));
    }
}
