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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HashedNotePasswordRepositoryTest {
    private static final String PASSWORD = "PASSWORD";
    private static final String HASHED_PASSWORD = "HASHED_PASSWORD";

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private PasswordHasher passwordHasher;
    @InjectMocks
    private HashedNotePasswordRepository hashedNotePasswordRepository;
    private Note note;
    private Note hashedPasswordNote;

    @BeforeEach
    void setUp() {
        note = Note.builder()
                .password(PASSWORD)
                .build();

        hashedPasswordNote = Note.builder()
                .password(HASHED_PASSWORD)
                .build();
    }

    @Test
    void createTest() {
        when(passwordHasher.hash(PASSWORD))
                .thenReturn(HASHED_PASSWORD);

        hashedNotePasswordRepository.create(note);

        verify(passwordHasher).hash(PASSWORD);
        verify(noteRepository).create(hashedPasswordNote);
    }

    @Test
    void updateTest() {
        when(passwordHasher.hash(PASSWORD))
                .thenReturn(HASHED_PASSWORD);

        hashedNotePasswordRepository.update(note);

        verify(passwordHasher).hash(PASSWORD);
        verify(noteRepository).update(hashedPasswordNote);
    }
}