package org.frozenlens.api.dto;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty(message="Username/email required")
    private String Username;
    @NotEmpty(message="Password required")
    private String password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
