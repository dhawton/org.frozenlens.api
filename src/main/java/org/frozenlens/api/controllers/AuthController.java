package org.frozenlens.api.controllers;

import org.frozenlens.api.dto.LoginRequest;
import org.frozenlens.api.dto.RegisterRequest;
import org.frozenlens.api.dto.TokenResponse;
import org.frozenlens.api.exception.GeneralException;
import org.frozenlens.api.exception.ValidationException;
import org.frozenlens.api.security.JwtUtils;
import org.frozenlens.api.security.UserDetailsImpl;
import org.frozenlens.api.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<? extends Object> login(@Valid @RequestBody LoginRequest loginRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch(AuthenticationException e) {
            log.warn("Authentication failed for: " + loginRequest.getUsername());
            throw new GeneralException("Authentication Failed", "", HttpStatus.FORBIDDEN);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        log.debug(String.format("Login successful for %s", userDetails.getUsername()));
        return new ResponseEntity<>(new TokenResponse(jwt, "Bearer", userDetails.getUsername()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        authService.register(registerRequest);
        log.info(String.format("User Registered (email='%s', username='%s'", registerRequest.getEmail(), registerRequest.getUsername()));
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
}
