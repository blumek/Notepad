package com.blumek.notepad.adapter.repository;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentEncoder;
import com.blumek.notepad.domain.port.NoteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EncodedNoteContentRepositoryTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String ENCODED_NOTE_CONTENT = "ENCODED_NOTE_CONTENT";

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NoteContentEncoder noteContentEncoder;
    @InjectMocks
    private EncodedNoteContentRepository encodedNoteContentRepository;

    private Note note;
    private Note encodedNote;

    @BeforeEach
    void setUp() {
        note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .build();

        encodedNote = note.toBuilder()
                .content(ENCODED_NOTE_CONTENT)
                .build();
    }

    @Test
    void createTest() {
        when(noteContentEncoder.encodeContent(note))
                .thenReturn(encodedNote);

        encodedNoteContentRepository.create(note);

        verify(noteRepository).create(encodedNote);
    }

    @Test
    void updateTest() {
        when(noteContentEncoder.encodeContent(note))
                .thenReturn(encodedNote);

        encodedNoteContentRepository.update(note);

        verify(noteRepository).update(encodedNote);
    }
}