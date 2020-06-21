package com.blumek.notepad.adapter.password_validator;

import com.blumek.notepad.domain.port.PasswordValidator;

import static org.mindrot.jbcrypt.BCrypt.checkpw;

public class BCryptPasswordValidator implements PasswordValidator {
    @Override
    public boolean validate(String password, String hashedPassword) {
        return checkpw(password, hashedPassword);
    }
}
