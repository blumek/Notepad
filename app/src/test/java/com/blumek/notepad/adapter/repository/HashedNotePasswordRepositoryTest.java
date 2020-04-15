package com.blumek.notepad.adapter.repository;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.domain.port.PasswordHasher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HashedNotePasswordRepositoryTest {
    private static final String PASSWORD = "PASSWORD";
    private static final String HASHED_PASSWORD = "HASHED_PASSWORD";
    private static final String NOTE_TITLE = "NOTE_TITLE";

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private PasswordHasher passwordHasher;
    @InjectMocks
    private HashedNotePasswordRepository hashedNotePasswordRepository;

    private Note note;
    private Note hashedPasswordNote;
    private Note noteWithoutPassword;

    @BeforeEach
    void setUp() {
        note = Note.builder()
                .password(PASSWORD)
                .build();

        hashedPasswordNote = Note.builder()
                .password(HASHED_PASSWORD)
                .build();

        noteWithoutPassword = Note.builder()
                .title(NOTE_TITLE)
                .build();
    }

    @Test
    void createTest_passwordAvailable() {
        when(passwordHasher.hash(PASSWORD))
                .thenReturn(HASHED_PASSWORD);

        hashedNotePasswordRepository.create(note);

        verify(passwordHasher).hash(PASSWORD);
        verify(noteRepository).create(hashedPasswordNote);
    }

    @Test
    void createTest_passwordNotAvailable() {
        hashedNotePasswordRepository.create(noteWithoutPassword);

        verify(noteRepository).create(noteWithoutPassword);
    }

    @Test
    void updateTest_passwordAvailable() {
        when(passwordHasher.hash(PASSWORD))
                .thenReturn(HASHED_PASSWORD);

        hashedNotePasswordRepository.update(note);

        verify(passwordHasher).hash(PASSWORD);
        verify(noteRepository).update(hashedPasswordNote);
    }

    @Test
    void updateTest_passwordNotAvailable() {
        hashedNotePasswordRepository.update(noteWithoutPassword);

        verify(noteRepository).update(noteWithoutPassword);
    }
}