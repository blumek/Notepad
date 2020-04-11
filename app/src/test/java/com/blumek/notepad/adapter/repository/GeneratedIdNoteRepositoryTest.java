package com.blumek.notepad.adapter.repository;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.IdGenerator;
import com.blumek.notepad.domain.port.NoteRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GeneratedIdNoteRepositoryTest {
    private static final String GENERATED_ID = "GENERATED_ID";
    private static final String NOTE_TITLE = "NOTE_TITLE";

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private IdGenerator idGenerator;
    @InjectMocks
    private GeneratedIdNoteRepository generatedIdNoteRepository;

    @Test
    void createTest() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .build();

        Note noteWithGeneratedId = note.toBuilder()
                .id(GENERATED_ID)
                .build();

        when(idGenerator.generate())
                .thenReturn(GENERATED_ID);

        generatedIdNoteRepository.create(note);

        verify(noteRepository).create(noteWithGeneratedId);
    }
}