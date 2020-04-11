package com.blumek.notepad.adapter.repository;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteEncoder;
import com.blumek.notepad.domain.port.NoteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EncodedNoteRepositoryTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String NOTE_PASSWORD = "NOTE_PASSWORD";
    private static final String ENCODED_NOTE_CONTENT = "ENCODED_NOTE_CONTENT";
    private static final String ENCODED_NOTE_PASSWORD = "ENCODED_NOTE_PASSWORD";

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NoteEncoder noteEncoder;
    @InjectMocks
    private EncodedNoteRepository encodedNoteRepository;

    private Note note;
    private Note encodedNote;

    @BeforeEach
    void setUp() {
        note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        encodedNote = note.toBuilder()
                .content(ENCODED_NOTE_CONTENT)
                .password(ENCODED_NOTE_PASSWORD)
                .build();
    }

    @Test
    void createTest() {
        when(noteEncoder.encode(note))
                .thenReturn(encodedNote);

        encodedNoteRepository.create(note);

        verify(noteRepository).create(encodedNote);
    }

    @Test
    void updateTest() {
        when(noteEncoder.encode(note))
                .thenReturn(encodedNote);

        encodedNoteRepository.update(note);

        verify(noteRepository).update(encodedNote);
    }
}