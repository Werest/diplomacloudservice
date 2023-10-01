package ru.werest.diplomacloudservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthResponse {

    @JsonProperty(value = "auth-token")
    private String authToken;
}
