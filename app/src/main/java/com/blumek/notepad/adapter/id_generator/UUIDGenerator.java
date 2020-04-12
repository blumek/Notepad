package com.blumek.notepad.adapter.id_generator;

import com.blumek.notepad.domain.port.IdGenerator;

import java.util.UUID;

public class UUIDGenerator implements IdGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
