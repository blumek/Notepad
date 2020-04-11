package com.blumek.notepad.adapter.password_hasher;

import com.blumek.notepad.domain.port.PasswordHasher;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class SHA256PasswordHasher implements PasswordHasher {

    @Override
    public String hash(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }
}
