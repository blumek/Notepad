package com.blumek.notepad.usecase;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.IdGenerator;
import com.blumek.notepad.domain.port.NoteEncoder;
import com.blumek.notepad.domain.port.NoteRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CreateNoteTest {
    private static final String GENERATED_ID = "GENERATED_ID";
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_PASSWORD = "NOTE_PASSWORD";
    private static final String ENCODED_NOTE_CONTENT = "ENCODED_NOTE_CONTENT";
    private static final String ENCODED_NOTE_PASSWORD = "ENCODED_NOTE_PASSWORD";

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private IdGenerator idGenerator;
    @Mock
    private NoteEncoder noteEncoder;
    @InjectMocks
    private CreateNote createNote;

    @Test
    void createTest_validNote() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        Note noteWithGeneratedId = note.toBuilder()
                .id(GENERATED_ID)
                .build();

        Note encodedNote = noteWithGeneratedId.toBuilder()
                .content(ENCODED_NOTE_CONTENT)
                .password(ENCODED_NOTE_PASSWORD)
                .build();

        Note returnedNote = encodedNote.toBuilder()
                .content(NOTE_CONTENT)
                .build();

        when(idGenerator.generate())
                .thenReturn(GENERATED_ID);

        when(noteEncoder.encode(any(Note.class)))
                .thenReturn(encodedNote);

        assertEquals(returnedNote, createNote.create(note));

        verify(idGenerator, times(1)).generate();
        verify(noteEncoder, times(1)).encode(any(Note.class));
        verify(noteRepository, times(1)).create(encodedNote);
    }
}