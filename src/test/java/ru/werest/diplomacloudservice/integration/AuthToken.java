package ru.werest.diplomacloudservice.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthToken {
    @JsonProperty("auth-token")
    private String authToken;
}
