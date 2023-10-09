package ru.werest.diplomacloudservice.exception;

public class AuthicatedException extends RuntimeException{
    public AuthicatedException(String message) {
        super(message);
    }
}
