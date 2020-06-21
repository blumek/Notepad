package com.blumek.notepad.adapter.password_validator;

import com.blumek.notepad.adapter.password_hasher.SHA256PasswordHasher;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SHA256PasswordValidatorTest {
    private static final String PASSWORD = "PASSWORD";
    private static final String HASHED_PASSWORD = "HASHED_PASSWORD";
    private static final String ANOTHER_HASHED_PASSWORD = "ANOTHER_HASHED_PASSWORD";

    @InjectMocks
    private SHA256PasswordValidator sha256PasswordValidator;
    @Mock
    private SHA256PasswordHasher sha256PasswordHasher;


    @Test
    void validateTest_samePasswords() {
        when(sha256PasswordHasher.hash(PASSWORD))
                .thenReturn(HASHED_PASSWORD);

        assertTrue(sha256PasswordValidator.validate(PASSWORD, HASHED_PASSWORD));
    }

    @Test
    void validateTest_differentPasswords() {
        when(sha256PasswordHasher.hash(PASSWORD))
                .thenReturn(HASHED_PASSWORD);

        assertFalse(sha256PasswordValidator.validate(PASSWORD, ANOTHER_HASHED_PASSWORD));
    }
}