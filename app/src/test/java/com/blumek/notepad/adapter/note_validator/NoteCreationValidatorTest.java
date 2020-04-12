package com.blumek.notepad.adapter.note_validator;

import com.blumek.notepad.domain.entity.Note;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteCreationValidatorTest {
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String EMPTY_TITLE = "";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String EMPTY_CONTENT = "";

    private NoteCreationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new NoteCreationValidator();
    }

    @Test
    void isValidTest_nullPassed() {
        assertFalse(validator.isValid(null));
    }

    @Test
    void isValidTest_noteWithNullTitle() {
        Note note = Note.builder()
                .title(null)
                .content(NOTE_CONTENT)
                .build();

        assertFalse(validator.isValid(note));
    }

    @Test
    void isValidTest_noteWithEmptyTitle() {
        Note note = Note.builder()
                .title(EMPTY_TITLE)
                .content(NOTE_CONTENT)
                .build();

        assertFalse(validator.isValid(note));
    }

    @Test
    void isValidTest_noteWithNullContent() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .content(null)
                .build();

        assertFalse(validator.isValid(note));
    }

    @Test
    void isValidTest_noteWithEmptyContent() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .content(EMPTY_CONTENT)
                .build();

        assertFalse(validator.isValid(note));
    }

    @Test
    void isValidTest_correctNote() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        assertTrue(validator.isValid(note));
    }
}