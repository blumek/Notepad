package com.blumek.notepad.adapter.note_content_encoder;

import com.blumek.notepad.domain.entity.Note;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Base64NoteContentEncoderTest {
    private static final String NOTE_CONTENT = "NOTE_CONTENT \n" +
            "ANOTHER_LINE_OF_CONTENT";
    private static final String ENCODED_NOTE_CONTENT =
            "Tk9URV9DT05URU5UIApBTk9USEVSX0xJTkVfT0ZfQ09OVEVOVA==";
    private static final String EMPTY_CONTENT = "";

    private Base64NoteContentEncoder encoder;

    @BeforeEach
    void setUp() {
        encoder = new Base64NoteContentEncoder();
    }

    @Test
    void encodeContentTest_noteWithNonEmptyContent() {
        Note note = Note.builder()
                .content(NOTE_CONTENT)
                .build();

        Note encodedNote = Note.builder()
                .content(ENCODED_NOTE_CONTENT)
                .build();

        assertEquals(encodedNote, encoder.encodeContent(note));
    }

    @Test
    void encodeContentTest_noteWithEmptyContent() {
        Note note = Note.builder()
                .content(EMPTY_CONTENT)
                .build();

        assertEquals(note, encoder.encodeContent(note));
    }

    @Test
    void encodeContentTest_noteWithNullAsContent() {
        Note note = Note.builder()
                .content(null)
                .build();

        assertEquals(note, encoder.encodeContent(note));
    }
}