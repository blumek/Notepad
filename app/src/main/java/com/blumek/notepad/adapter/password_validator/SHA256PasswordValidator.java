package com.blumek.notepad.adapter.password_validator;

import com.blumek.notepad.adapter.password_hasher.SHA256PasswordHasher;
import com.blumek.notepad.domain.port.PasswordValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SHA256PasswordValidator implements PasswordValidator {
    private final SHA256PasswordHasher passwordHasher;

    @Override
    public boolean validate(String password, String hashedPassword) {
        String hashedTestPassword = passwordHasher.hash(password);
        return hashedTestPassword.equals(hashedPassword);
    }
}
