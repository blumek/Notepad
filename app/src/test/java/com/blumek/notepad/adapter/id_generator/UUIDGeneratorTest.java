package com.blumek.notepad.adapter.id_generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class UUIDGeneratorTest {
    private UUIDGenerator uuidGenerator;

    @BeforeEach
    void setUp() {
        uuidGenerator = new UUIDGenerator();
    }

    @Test
    void generateTest() {
        String generatedId = uuidGenerator.generate();
        assertThat(generatedId, is(not(emptyString())));
    }
}