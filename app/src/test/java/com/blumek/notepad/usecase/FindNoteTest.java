package com.blumek.notepad.usecase;

import androidx.lifecycle.MutableLiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.extension.InstantTaskExecutorExtension;
import com.google.common.collect.Lists;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
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
class FindNoteTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String ANOTHER_NOTE_ID = "ANOTHER_NOTE_ID";

    @Mock
    private NoteRepository noteRepository;
    @InjectMocks
    private FindNote findNote;

    private Note note;

    @BeforeEach
    void setUp() {
        note = Note.builder()
                .id(NOTE_ID)
                .build();
    }

    @Test
    void findByIdTest_noteNotExists() {
        when(noteRepository.findById(NOTE_ID))
                .thenReturn(new MutableLiveData<>(Optional.empty()));

        findNote.findById(NOTE_ID).observeForever(foundNote -> {
            assertEquals(Optional.empty(), foundNote);

            verify(noteRepository).findById(NOTE_ID);
        });
    }

    @Test
    void findByIdTest_noteExists() {
        when(noteRepository.findById(NOTE_ID))
                .thenReturn(new MutableLiveData<>(Optional.of(note)));

        findNote.findById(NOTE_ID).observeForever(foundNote -> {
            assertEquals(Optional.of(note), foundNote);

            verify(noteRepository).findById(NOTE_ID);
        });
    }

    @Test
    void findAllTest_notesNotAvailable() {
        when(noteRepository.findAll())
                .thenReturn(new MutableLiveData<>(Lists.newArrayList()));

        findNote.findAll().observeForever(notes -> {
            assertThat(notes, is(empty()));
            verify(noteRepository).findAll();
        });
    }

    @Test
    void findAllTest_twoNotesAvailable() {
        Note secondNote = Note.builder()
                .id(ANOTHER_NOTE_ID)
                .build();

        when(noteRepository.findAll())
                .thenReturn(new MutableLiveData<>(Lists.newArrayList(
                        note,
                        secondNote
                )));

        findNote.findAll().observeForever(notes -> {
            assertThat(notes, containsInAnyOrder(note, secondNote));
            verify(noteRepository).findAll();
        });
    }
}