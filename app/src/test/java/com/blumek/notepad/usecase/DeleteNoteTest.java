package com.blumek.notepad.usecase;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteNoteTest {
    private static final String NOTE_ID = "NOTE_ID";

    @Mock
    private NoteRepository noteRepository;
    @InjectMocks
    private DeleteNote deleteNote;

    @Test
    void deleteNoteTest() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .build();

        deleteNote.delete(note);

        verify(noteRepository, times(1)).delete(note);
    }
}