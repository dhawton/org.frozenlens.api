package org.frozenlens.api.service;

import org.frozenlens.api.data.entity.User;
import org.frozenlens.api.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setUsername(registerRequest.getUsername());

    }
}
