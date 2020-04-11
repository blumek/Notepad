package com.blumek.notepad.domain.port;

public interface PasswordHasher {
    String hash(String password);
}
