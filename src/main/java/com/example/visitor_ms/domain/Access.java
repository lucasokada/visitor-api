package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidAccessException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Access {
    private String username;
    private String password;

    public Access(String username, String password) {
        this.username = validateUsername(username);
        this.password = validatePassword(password);
    }

    private String validateUsername(String username) {
        if (!isValidUsername(username)) {
            throw new InvalidAccessException("Invalid username: must be alphanumeric, cannot be only numbers, and must be between 5 and 20 characters.");
        }
        return username;
    }

    private boolean isValidUsername(String username) {
        return username.matches("^(?!\\d+$)[a-zA-Z0-9]{5,20}$");
    }

    private String validatePassword(String password) {
        if (!isValidPassword(password)) {
            throw new InvalidAccessException("Invalid password: must be between 8 and 20 characters, contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }
        return password;
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$");
    }
}

