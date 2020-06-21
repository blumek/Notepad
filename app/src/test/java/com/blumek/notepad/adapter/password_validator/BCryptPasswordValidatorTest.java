package com.blumek.notepad.adapter.password_validator;

import com.blumek.notepad.adapter.password_hasher.BCryptPasswordHasher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordValidatorTest {
    private static final String PASSWORD = "PASSWORD";
    public static final String DIFF_PASSWORD = "DIFF_PASSWORD";

    private BCryptPasswordValidator bCryptPasswordValidator;
    private BCryptPasswordHasher bCryptPasswordHasher;

    @BeforeEach
    void setUp() {
        bCryptPasswordValidator = new BCryptPasswordValidator();
        bCryptPasswordHasher = new BCryptPasswordHasher();
    }

    @Test
    void validateTest_samePasswords() {
        String hashedPassword = bCryptPasswordHasher.hash(PASSWORD);
        assertTrue(bCryptPasswordValidator.validate(PASSWORD, hashedPassword));
    }

    @Test
    void validateTest_differentPasswords() {
        String hashedPassword = bCryptPasswordHasher.hash(PASSWORD);
        assertFalse(bCryptPasswordValidator.validate(DIFF_PASSWORD, hashedPassword));
    }
}