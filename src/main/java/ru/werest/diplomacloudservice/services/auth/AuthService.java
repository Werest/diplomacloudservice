package ru.werest.diplomacloudservice.services.auth;

import ru.werest.diplomacloudservice.dto.LoginRequest;

public interface AuthService {
    String login(LoginRequest request);
}
