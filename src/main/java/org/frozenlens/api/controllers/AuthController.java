package org.frozenlens.api.controllers;

import org.frozenlens.api.dto.RegisterRequest;
import org.frozenlens.api.exception.ErrorResponse;
import org.frozenlens.api.exception.ValidationException;
import org.frozenlens.api.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
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
