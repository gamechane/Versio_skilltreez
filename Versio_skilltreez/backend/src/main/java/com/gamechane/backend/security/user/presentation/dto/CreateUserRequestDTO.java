package com.gamechane.backend.security.user.presentation.dto;

import java.util.List;

public class CreateUserRequestDTO {
    public String username;
    public String emailAddress;
    public String password;
    public List<String> roles;

    public CreateUserRequestDTO() {

    }
}
