package com.assessment.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CoreError {
    USER_EMAIL_EXISTS(HttpStatus.CONFLICT, "User with the same email already exists."),
    USER_NOT_EXISTS(HttpStatus.NOT_FOUND, "User does not exists.");

    private final HttpStatus code;
    private final String description;

    private CoreError(HttpStatus code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
