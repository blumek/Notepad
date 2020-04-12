package com.blumek.notepad.usecase;

import androidx.lifecycle.MutableLiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.extension.InstantTaskExecutorExtension;
import com.google.common.collect.Lists;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    void findAll_notesNotAvailable() {
        when(noteRepository.findAll())
                .thenReturn(new MutableLiveData<>(Lists.newArrayList()));

        findNote.findAll().observeForever(notes -> {
            assertThat(notes, is(empty()));
            verify(noteRepository).findAll();
        });
    }

    @Test
    void findAll_twoNotesAvailable() {
        Note firstNote = Note.builder()
                .id(NOTE_ID)
                .build();

        Note secondNote = Note.builder()
                .id(ANOTHER_NOTE_ID)
                .build();

        when(noteRepository.findAll())
                .thenReturn(new MutableLiveData<>(Lists.newArrayList(
                        firstNote,
                        secondNote
                )));

        findNote.findAll().observeForever(notes -> {
            assertThat(notes, containsInAnyOrder(firstNote, secondNote));
            verify(noteRepository).findAll();
        });
    }
}