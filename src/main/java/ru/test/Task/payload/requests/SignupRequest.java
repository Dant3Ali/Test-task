package ru.test.Task.payload.requests;

import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    
    private String login;
    private String password;
    private Set<String> role;
}
