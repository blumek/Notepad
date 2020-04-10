package com.blumek.notepad.usecase;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.exception.InvalidNoteException;
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
class UpdateNoteTest {
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
    private UpdateNote updateNote;

    @Test
    void updateTest_noteHasId() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        Note encodedNote = note.toBuilder()
                .content(ENCODED_NOTE_CONTENT)
                .password(ENCODED_NOTE_PASSWORD)
                .build();

        Note returnedNote = encodedNote.toBuilder()
                .content(note.getContent())
                .build();

        when(noteEncoder.encode(any(Note.class)))
                .thenReturn(encodedNote);

        assertEquals(returnedNote, updateNote.update(note));

        verify(noteEncoder, times(1)).encode(any(Note.class));
        verify(noteRepository, times(1)).update(encodedNote);
    }

    @Test
    void updateTest_noteHasNoId() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();

        assertThrows(InvalidNoteException.class, () -> updateNote.update(note));
    }
}