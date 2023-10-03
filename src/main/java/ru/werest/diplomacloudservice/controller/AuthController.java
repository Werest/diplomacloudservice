package ru.werest.diplomacloudservice.controller;

import jakarta.security.auth.message.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.werest.diplomacloudservice.request.LoginRequest;
import ru.werest.diplomacloudservice.response.AuthResponse;
import ru.werest.diplomacloudservice.services.AuthService;

@RestController
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) throws AuthException {
        AuthResponse response = new AuthResponse();
        response.setAuthToken(service.login(request));
        return ResponseEntity.ok().body(response);
    }


}
