package com.blumek.notepad.usecase;

import androidx.lifecycle.MutableLiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.domain.port.PasswordHasher;
import com.blumek.notepad.extension.InstantTaskExecutorExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith({
        MockitoExtension.class,
        InstantTaskExecutorExtension.class
})
class NoteAuthenticationTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String HASHED_PASSWORD = "HASHED_PASSWORD";
    private static final String PASSWORD = "PASSWORD";
    private static final String WRONG_HASHED_PASSWORD = "WRONG_HASHED_PASSWORD";

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private PasswordHasher passwordHasher;
    @InjectMocks
    private NoteAuthentication noteAuthentication;

    @Test
    void authenticateWithPasswordTest_correctPassword() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .password(HASHED_PASSWORD)
                .build();

        when(noteRepository.findById(NOTE_ID))
                .thenReturn(new MutableLiveData<>(Optional.of(note)));

        when(passwordHasher.hash(PASSWORD))
                .thenReturn(HASHED_PASSWORD);

        noteAuthentication.authenticateWithPassword(NOTE_ID, PASSWORD)
                .observeForever(authenticationResult -> {
                    assertTrue(authenticationResult);

                    verify(noteRepository).findById(NOTE_ID);
                    verify(passwordHasher).hash(PASSWORD);
                });
    }

    @Test
    void authenticateWithPasswordTest_wrongPassword() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .password(HASHED_PASSWORD)
                .build();

        when(noteRepository.findById(NOTE_ID))
                .thenReturn(new MutableLiveData<>(Optional.of(note)));

        when(passwordHasher.hash(PASSWORD))
                .thenReturn(WRONG_HASHED_PASSWORD);

        noteAuthentication.authenticateWithPassword(NOTE_ID, PASSWORD)
                .observeForever(authenticationResult -> {
                    assertFalse(authenticationResult);

                    verify(noteRepository).findById(NOTE_ID);
                    verify(passwordHasher).hash(PASSWORD);
                });
    }

    @Test
    void authenticateWithPasswordTest_noteWithGivenIdNotExists() {
        when(noteRepository.findById(NOTE_ID))
                .thenReturn(new MutableLiveData<>(Optional.empty()));

        assertThrows(IllegalArgumentException.class, () ->
                noteAuthentication.authenticateWithPassword(NOTE_ID, PASSWORD)
                        .observeForever(authenticationResult ->
                                verify(noteRepository).findById(NOTE_ID)));
    }
}