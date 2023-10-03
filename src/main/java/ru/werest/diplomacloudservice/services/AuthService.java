package ru.werest.diplomacloudservice.services;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.werest.diplomacloudservice.jwt.JWTHelper;
import ru.werest.diplomacloudservice.request.LoginRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserService userService;

    private final JWTHelper jwtHelper;

    private final AuthenticationManager authenticationManager;

    public String login(LoginRequest request) throws AuthException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        } catch (BadCredentialsException e) {
            log.error("Wrong password = " + request.getLogin());
            throw new AuthException("Bad auth");
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
        return jwtHelper.generateToken(userDetails.getUsername());
    }

}
