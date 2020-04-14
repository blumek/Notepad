package com.blumek.notepad.application.model;

import com.blumek.notepad.domain.entity.Note;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewNoteTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String NOTE_PASSWORD = "NOTE_PASSWORD";

    @Test
    void builderTest_onlyTitle() {
        ViewNote viewNote = ViewNote.builder()
                .title(NOTE_TITLE)
                .build();

        assertEquals(NOTE_TITLE, viewNote.getTitle());
    }

    @Test
    void builderTest_onlyContent() {
        ViewNote viewNote = ViewNote.builder()
                .content(NOTE_CONTENT)
                .build();

        assertEquals(NOTE_CONTENT, viewNote.getContent());
    }

    @Test
    void fromTest() {
        ViewNote viewNote = ViewNote.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        assertEquals(viewNote, ViewNote.from(note));
    }

    @Test
    void equalsTest_equalObjects() {
        ViewNote viewNote = ViewNote.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        ViewNote anotherViewNote = ViewNote.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        assertEquals(viewNote, anotherViewNote);
    }

    @Test
    void equalsTest_notEqualObjects() {
        ViewNote viewNote = ViewNote.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        ViewNote anotherViewNote = ViewNote.builder()
                .title(NOTE_TITLE)
                .build();

        assertNotEquals(viewNote, anotherViewNote);
    }

    @Test
    void hashCodeTest_equalObjects() {
        ViewNote viewNote = ViewNote.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        ViewNote anotherViewNote = ViewNote.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        assertEquals(viewNote.hashCode(), anotherViewNote.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        ViewNote viewNote = ViewNote.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        ViewNote anotherViewNote = ViewNote.builder()
                .title(NOTE_TITLE)
                .build();

        assertNotEquals(viewNote.hashCode(), anotherViewNote.hashCode());
    }
}