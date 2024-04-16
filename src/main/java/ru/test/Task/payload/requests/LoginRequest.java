package ru.test.Task.payload.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
