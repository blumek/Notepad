package com.blumek.notepad.usecase;

import androidx.lifecycle.MutableLiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FindNoteTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String ANOTHER_NOTE_ID = "ANOTHER_NOTE_ID";

    @Mock
    private NoteRepository noteRepository;
    @InjectMocks
    private FindNote findNote;

    @Test
    void findById_noteWithGivenIdNotFound() {
        when(noteRepository.findById(anyString()))
                .thenReturn(new MutableLiveData<>(null));

        assertNull(findNote.findById(NOTE_ID).getValue());
    }

    @Test
    void findById_noteWithGivenIdAvailable() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .build();

        when(noteRepository.findById(anyString()))
                .thenReturn(new MutableLiveData<>(note));

        assertEquals(note, findNote.findById(NOTE_ID).getValue());
    }

    @Test
    void findAll_noNotesAvailable() {
        when(noteRepository.findAll())
                .thenReturn(new MutableLiveData<>(Lists.newArrayList()));

        assertEquals(Lists.newArrayList(), findNote.findAll().getValue());
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

        assertEquals(Lists.newArrayList(firstNote, secondNote), findNote.findAll().getValue());
    }
}