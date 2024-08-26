package com.damazon.dto;

public class AuthenticationResponse {
    private String userName;
    private String role;
    private String token;

    public AuthenticationResponse(String userName, String role, String token) {
        this.userName = userName;
        this.role = role;
        this.token = token;
    }

    // Getters and setters
    public String getUserId() {
        return userName;
    }

    public void setUserId(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

