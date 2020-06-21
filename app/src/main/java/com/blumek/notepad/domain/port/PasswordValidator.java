package com.blumek.notepad.domain.port;

public interface PasswordValidator {
    boolean validate(String password, String hashedPassword);
}
