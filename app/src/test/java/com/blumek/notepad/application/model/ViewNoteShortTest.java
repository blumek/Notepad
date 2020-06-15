package com.blumek.notepad.application.model;

import com.blumek.notepad.application.notes.ViewNoteShort;
import com.blumek.notepad.domain.entity.Note;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewNoteShortTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String NOTE_PASSWORD = "NOTE_PASSWORD";

    @Test
    void builderTest_onlyId() {
        ViewNoteShort viewNoteShort = ViewNoteShort.builder()
                .id(NOTE_ID)
                .build();

        assertEquals(NOTE_ID, viewNoteShort.getId());
    }

    @Test
    void builderTest_onlyTitle() {
        ViewNoteShort viewNoteShort = ViewNoteShort.builder()
                .title(NOTE_TITLE)
                .build();

        assertEquals(NOTE_TITLE, viewNoteShort.getTitle());
    }

    @Test
    void builderTest_hasPassword() {
        ViewNoteShort viewNoteShort = ViewNoteShort.builder()
                .hasPassword(true)
                .build();

        assertTrue(viewNoteShort.hasPassword());
    }

    @Test
    void fromTest_hasPassword() {
        ViewNoteShort viewNoteShort = ViewNoteShort.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .hasPassword(true)
                .build();

        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        assertEquals(viewNoteShort, ViewNoteShort.from(note));
    }

    @Test
    void fromTest_noPassword() {
        ViewNoteShort viewNoteShort = ViewNoteShort.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .hasPassword(false)
                .build();

        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        assertEquals(viewNoteShort, ViewNoteShort.from(note));
    }

    @Test
    void equalsTest_equalObjects() {
        ViewNoteShort viewNoteShort = ViewNoteShort.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .hasPassword(true)
                .build();

        ViewNoteShort anotherViewNoteShort = ViewNoteShort.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .hasPassword(true)
                .build();

        assertEquals(viewNoteShort, anotherViewNoteShort);
    }

    @Test
    void equalsTest_notEqualObjects() {
        ViewNoteShort viewNoteShort = ViewNoteShort.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .hasPassword(true)
                .build();

        ViewNoteShort anotherViewNoteShort = ViewNoteShort.builder()
                .title(NOTE_TITLE)
                .hasPassword(false)
                .build();

        assertNotEquals(viewNoteShort, anotherViewNoteShort);
    }

    @Test
    void hashCodeTest_equalObjects() {
        ViewNoteShort viewNoteShort = ViewNoteShort.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .hasPassword(true)
                .build();

        ViewNoteShort anotherViewNoteShort = ViewNoteShort.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .hasPassword(true)
                .build();

        assertEquals(viewNoteShort.hashCode(), anotherViewNoteShort.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        ViewNoteShort viewNoteShort = ViewNoteShort.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .hasPassword(true)
                .build();

        ViewNoteShort anotherViewNoteShort = ViewNoteShort.builder()
                .title(NOTE_TITLE)
                .hasPassword(false)
                .build();

        assertNotEquals(viewNoteShort.hashCode(), anotherViewNoteShort.hashCode());
    }
}