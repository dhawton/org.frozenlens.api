package org.frozenlens.api.dto;

public class TokenResponse {
    private String token;
    private String type = "Bearer";
    private String username;

    public TokenResponse(String token, String type, String username) {
        this.token = token;
        this.type = type;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
