package com.blumek.notepad.adapter.password_hasher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SHA256PasswordHasherTest {
    private static final String PASSWORD = "PASSWORD";
    private static final String HASHED_PASSWORD = "0be64ae89ddd24e225434de95d501711339baeee18f009ba9b4369af27d30d60";

    private SHA256PasswordHasher passwordHasher;

    @BeforeEach
    void setUp() {
        passwordHasher = new SHA256PasswordHasher();
    }

    @Test
    void hashTest() {
        assertEquals(HASHED_PASSWORD, passwordHasher.hash(PASSWORD));
    }
}