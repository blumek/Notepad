package com.blumek.notepad.adapter.note_content_decoder;

import com.blumek.notepad.domain.entity.Note;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Base64NoteContentDecoderTest {
    private static final String ENCODED_NOTE_CONTENT =
            "Tk9URV9DT05URU5UIApBTk9USEVSX0xJTkVfT0ZfQ09OVEVOVA==";
    private static final String DECODED_NOTE_CONTENT = "NOTE_CONTENT \n" +
            "ANOTHER_LINE_OF_CONTENT";
    private static final String EMPTY_CONTENT = "";

    private Base64NoteContentDecoder decoder;

    @BeforeEach
    void setUp() {
        decoder = new Base64NoteContentDecoder();
    }

    @Test
    void decodeContentTest_noteWithContent() {
        Note note = Note.builder()
                .content(ENCODED_NOTE_CONTENT)
                .build();

        Note decodedContentNote = Note.builder()
                .content(DECODED_NOTE_CONTENT)
                .build();

        assertEquals(decodedContentNote, decoder.decodeContent(note));
    }

    @Test
    void decodeContentTest_noteWithEmptyContent() {
        Note note = Note.builder()
                .content(EMPTY_CONTENT)
                .build();

        assertEquals(note, decoder.decodeContent(note));
    }

    @Test
    void decodeContentTest_noteWithNullContent() {
        Note note = Note.builder()
                .content(null)
                .build();

        assertEquals(note, decoder.decodeContent(note));
    }
}