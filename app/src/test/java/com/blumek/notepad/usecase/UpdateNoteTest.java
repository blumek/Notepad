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
class UpdateNoteTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String NOTE_PASSWORD = "NOTE_PASSWORD";

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NoteValidator noteValidator;
    @InjectMocks
    private UpdateNote updateNote;

    @Test
    void updateTest_validNote() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        when(noteValidator.isValid(note))
                .thenReturn(true);

        updateNote.update(note);

        verify(noteRepository).update(note);
    }

    @Test
    void updateTest_noteHasNoId() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        when(noteValidator.isValid(note))
                .thenReturn(false);

        assertThrows(InvalidNoteException.class, () -> updateNote.update(note));
    }
}