package com.jeromerichard.pdfstream.Payload.request;

import jakarta.validation.constraints.NotBlank;

// Payload ? => it contains the actual data being transferred between the client and the server.
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
