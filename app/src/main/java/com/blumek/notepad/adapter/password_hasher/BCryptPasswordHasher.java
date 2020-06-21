package com.blumek.notepad.adapter.password_hasher;

import com.blumek.notepad.domain.port.PasswordHasher;


import lombok.RequiredArgsConstructor;

import static org.mindrot.jbcrypt.BCrypt.*;

@RequiredArgsConstructor
public class BCryptPasswordHasher implements PasswordHasher {
    @Override
    public String hash(String password) {
        return hashpw(password, gensalt());
    }
}
