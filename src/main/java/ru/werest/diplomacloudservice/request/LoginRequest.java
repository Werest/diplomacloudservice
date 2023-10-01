package ru.werest.diplomacloudservice.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
