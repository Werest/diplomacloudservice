package ru.werest.diplomacloudservice.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.werest.diplomacloudservice.AbstractTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JWTHelperTest extends AbstractTest {

    @Autowired
    JWTHelper jwtHelper;

    String username = "test@test.ru";
    String header = "Bearer ";
    String generateToken = "";

    UserDetails userDetails = Mockito.mock(UserDetails.class);

    @BeforeEach
    void setUp() {
        generateToken = jwtHelper.generateToken(username);
        header += generateToken;
        Mockito.when(userDetails.getUsername()).thenReturn(username);
    }

    @Test
    void generateToken() {
        String result = jwtHelper.generateToken(username);
        assertNotNull(result);
    }

    @Test
    void getUserNameFromToken() {
        String result = jwtHelper.getUserNameFromToken(generateToken);
        assertEquals(result, username);
    }

    @Test
    void isValidateToken() {
        Boolean isValidToken = jwtHelper.isValidateToken(generateToken, userDetails);
        System.out.println(isValidToken);
    }
}