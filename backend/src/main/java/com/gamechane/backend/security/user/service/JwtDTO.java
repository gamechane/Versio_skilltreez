package com.gamechane.backend.security.user.service;

public class JwtDTO {
    private final String token;

    public JwtDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
