package com.blumek.notepad.adapter.repository.model;

import com.blumek.notepad.domain.entity.Note;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomNoteTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String NOTE_PASSWORD = "NOTE_PASSWORD";
    private static final String ANOTHER_NOTE_ID = "ANOTHER_NOTE_ID";
    private static final String ANOTHER_NOTE_TITLE = "ANOTHER_NOTE_TITLE";
    private static final String ANOTHER_NOTE_CONTENT = "ANOTHER_NOTE_CONTENT";
    private static final String ANOTHER_NOTE_PASSWORD = "ANOTHER_NOTE_PASSWORD";

    private Note note;
    private RoomNote roomNote;

    @BeforeEach
    void setUp() {
        note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        roomNote = RoomNote.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();
    }

    @Test
    void fromTest() {
        assertEquals(roomNote, RoomNote.from(note));
    }

    @Test
    void toNoteTest() {
        assertEquals(note, roomNote.toNote());
    }

    @Test
    void equalsTest_equalObjects() {
        RoomNote anotherRoomNote = RoomNote.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        assertEquals(roomNote, anotherRoomNote);
    }

    @Test
    void equalsTest_notEqualObjects() {
        RoomNote anotherRoomNote = RoomNote.builder()
                .id(ANOTHER_NOTE_ID)
                .title(ANOTHER_NOTE_TITLE)
                .content(ANOTHER_NOTE_CONTENT)
                .password(ANOTHER_NOTE_PASSWORD)
                .build();

        assertNotEquals(roomNote, anotherRoomNote);
    }

    @Test
    void hashCodeTest_equalObjects() {
        RoomNote anotherRoomNote = RoomNote.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        assertEquals(roomNote.hashCode(), anotherRoomNote.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        RoomNote anotherRoomNote = RoomNote.builder()
                .id(ANOTHER_NOTE_ID)
                .title(ANOTHER_NOTE_TITLE)
                .content(ANOTHER_NOTE_CONTENT)
                .password(ANOTHER_NOTE_PASSWORD)
                .build();

        assertNotEquals(roomNote.hashCode(), anotherRoomNote.hashCode());
    }
}