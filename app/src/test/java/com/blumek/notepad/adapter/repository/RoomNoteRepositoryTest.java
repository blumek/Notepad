package com.blumek.notepad.adapter.repository;

import androidx.lifecycle.MutableLiveData;

import com.blumek.notepad.domain.entity.Note;
import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomNoteRepositoryTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String SECOND_NOTE_ID = "SECOND_NOTE_ID";

    @Mock
    private NoteDao noteDao;
    @InjectMocks
    private RoomNoteRepository roomNoteRepository;

    @Test
    void findByIdTest_NoteWithGivenIdNotExists() {
        when(noteDao.findById(anyString()))
                .thenReturn(new MutableLiveData<Note>(null));

        assertNull(roomNoteRepository.findById(NOTE_ID).getValue());
        verify(noteDao, atLeastOnce()).findById(anyString());
    }

    @Test
    void findByIdTest_NoteWithGivenIdExists() {
        Note note = Note.builder()
                .id(NOTE_ID)
                .build();

        when(noteDao.findById(anyString()))
                .thenReturn(new MutableLiveData<>(note));

        assertEquals(note, roomNoteRepository.findById(NOTE_ID).getValue());
        verify(noteDao, atLeastOnce()).findById(anyString());
    }

    @Test
    void findAllTest_NotesNotAvailable() {
        when(noteDao.findAll())
                .thenReturn(new MutableLiveData<List<Note>>(Lists.<Note>newArrayList()));

        assertEquals(Lists.newArrayList(), roomNoteRepository.findAll().getValue());
        verify(noteDao, atLeastOnce()).findAll();
    }

    @Test
    void findAllTest_TwoNoteAvailable() {
        ArrayList<Note> notes = Lists.newArrayList(
                Note.builder()
                        .id(NOTE_ID)
                        .build(),
                Note.builder()
                        .id(SECOND_NOTE_ID)
                        .build()
        );

        when(noteDao.findAll())
                .thenReturn(new MutableLiveData<List<Note>>(notes));

        assertEquals(notes, roomNoteRepository.findAll().getValue());
        verify(noteDao, atLeastOnce()).findAll();
    }
}