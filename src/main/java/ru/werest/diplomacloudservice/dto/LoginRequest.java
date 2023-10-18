package ru.werest.diplomacloudservice.dto;

public record LoginRequest(String login, String password) {
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
