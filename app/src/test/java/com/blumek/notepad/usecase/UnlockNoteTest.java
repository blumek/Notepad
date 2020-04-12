package com.blumek.notepad.usecase;

import androidx.lifecycle.MutableLiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.domain.port.PasswordHasher;
import com.blumek.notepad.extension.InstantTaskExecutorExtension;
import com.google.common.base.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith({
        MockitoExtension.class,
        InstantTaskExecutorExtension.class
})
class UnlockNoteTest {
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
    private UnlockNote unlockNote;

    @Test
    void unlockNoteTest_noteAvailableAndPasswordIsCorrect() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .password(HASHED_PASSWORD)
                .build();

        when(noteRepository.findById(NOTE_ID))
                .thenReturn(new MutableLiveData<>(Optional.of(note)));

        when(passwordHasher.hash(PASSWORD))
                .thenReturn(HASHED_PASSWORD);

        unlockNote.unlockNote(NOTE_ID, PASSWORD).observeForever(unlockedNote -> {
            assertEquals(Optional.of(note), unlockedNote);

            verify(noteRepository).findById(NOTE_ID);
            verify(passwordHasher).hash(PASSWORD);
        });
    }

    @Test
    void unlockNoteTest_noteAvailableAndPasswordIsIncorrect() {
        Note note = Note.builder()
                .title(NOTE_TITLE)
                .password(HASHED_PASSWORD)
                .build();

        when(noteRepository.findById(NOTE_ID))
                .thenReturn(new MutableLiveData<>(Optional.of(note)));

        when(passwordHasher.hash(PASSWORD))
                .thenReturn(WRONG_HASHED_PASSWORD);

        unlockNote.unlockNote(NOTE_ID, PASSWORD).observeForever(unlockedNote -> {
            assertEquals(Optional.absent(), unlockedNote);

            verify(noteRepository).findById(NOTE_ID);
            verify(passwordHasher).hash(PASSWORD);
        });
    }

    @Test
    void unlockNoteTest_noteNotAvailable() {
        when(noteRepository.findById(NOTE_ID))
                .thenReturn(new MutableLiveData<>(Optional.absent()));

        unlockNote.unlockNote(NOTE_ID, PASSWORD).observeForever(unlockedNote -> {
            assertEquals(Optional.absent(), unlockedNote);

            verify(noteRepository).findById(NOTE_ID);
        });
    }
}