package com.blumek.notepad.adapter.password_hasher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordHasherTest {
    private static final String PASSWORD = "PASSWORD";

    private BCryptPasswordHasher bCryptPasswordHasher;

    @BeforeEach
    void setUp() {
        bCryptPasswordHasher = new BCryptPasswordHasher();
    }

    @Test
    void hashTest() {
        String hashedPassword = bCryptPasswordHasher.hash(PASSWORD);
        assertTrue(BCrypt.checkpw(PASSWORD, hashedPassword));
    }
}