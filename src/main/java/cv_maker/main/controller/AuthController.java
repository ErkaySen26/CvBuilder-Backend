package cv_maker.main.controller;

import cv_maker.main.dto.AuthRequest;
import cv_maker.main.dto.CreateUserRequest;
import cv_maker.main.dto.UserResponse;
import cv_maker.main.model.User;
import cv_maker.main.service.AuthService;
import cv_maker.main.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody CreateUserRequest request) {
        User user = service.createUser(request);
        return new UserResponse(user.getId(), user.getEmail(), user.getPassword());
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.email());
        }
        log.info("Invalid email: " + request.email());
        throw new UsernameNotFoundException("Invalid email: " + request.email());
    }
}
