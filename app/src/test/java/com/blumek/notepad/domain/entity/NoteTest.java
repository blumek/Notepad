package com.blumek.notepad.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String ANOTHER_NOTE_ID = "ANOTHER_NOTE_ID";
    private static final String ANOTHER_NOTE_TITLE = "ANOTHER_NOTE_TITLE";

    @Test
    void builderTest_onlyId() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .build();

        assertEquals(NOTE_ID, note.getId());
    }

    @Test
    void builderTest_onlyTitle() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .build();

        assertEquals(NOTE_TITLE, note.getTitle());
    }

    @Test
    void builderTest_onlyContent() {
        Note note = Note.builder()
                .content(NOTE_CONTENT)
                .build();

        assertEquals(NOTE_CONTENT, note.getContent());
    }

    @Test
    void equalsTest_equalObjects() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        Note anotherNote = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        assertEquals(anotherNote, note);
    }

    @Test
    void equalsTest_notEqualObjects() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        Note anotherNote = Note.builder()
                .id(ANOTHER_NOTE_ID)
                .title(ANOTHER_NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        assertNotEquals(anotherNote, note);
    }

    @Test
    void hashCodeTest_equalObjects() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        Note anotherNote = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        assertEquals(anotherNote.hashCode(), note.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        Note anotherNote = Note.builder()
                .id(ANOTHER_NOTE_ID)
                .title(ANOTHER_NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        assertNotEquals(anotherNote.hashCode(), note.hashCode());
    }
}