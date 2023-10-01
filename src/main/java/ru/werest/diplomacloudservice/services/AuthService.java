package ru.werest.diplomacloudservice.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.werest.diplomacloudservice.jwt.JWTHelper;
import ru.werest.diplomacloudservice.request.LoginRequest;

@Service
public class AuthService {

    private final UserService userService;

    private final JWTHelper jwtHelper;

    public AuthService(UserService userService, JWTHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    public String login(LoginRequest request) {
        UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
        return jwtHelper.generateToken(userDetails.getUsername());
    }

}
