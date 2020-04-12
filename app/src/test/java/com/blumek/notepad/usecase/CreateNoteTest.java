package com.blumek.notepad.usecase;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.exception.InvalidNoteException;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.domain.port.NoteValidator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CreateNoteTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_PASSWORD = "NOTE_PASSWORD";

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NoteValidator noteValidator;
    @InjectMocks
    private CreateNote createNote;

    @Test
    void createTest_validNote() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        when(noteValidator.isValid(any()))
                .thenReturn(true);

        createNote.create(note);

        verify(noteRepository).create(note);
    }

    @Test
    void createTest_invalidNote() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        when(noteValidator.isValid(any()))
                .thenReturn(false);

        assertThrows(InvalidNoteException.class, () -> createNote.create(note));
    }
}