package com.blumek.notepad.adapter.repository;

import androidx.lifecycle.MutableLiveData;

import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.adapter.repository.model.RoomNote;
import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.extension.InstantTaskExecutorExtension;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith({
        MockitoExtension.class,
        InstantTaskExecutorExtension.class
})
class RoomNoteRepositoryTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_CONTENT = "NOTE_CONTENT";
    private static final String NOTE_PASSWORD = "NOTE_PASSWORD";
    private static final String ANOTHER_NOTE_ID = "ANOTHER_NOTE_ID";
    private static final String ANOTHER_NOTE_TITLE = "ANOTHER_NOTE_TITLE";
    private static final String ANOTHER_NOTE_CONTENT = "ANOTHER_NOTE_CONTENT";
    private static final String ANOTHER_NOTE_PASSWORD = "ANOTHER_NOTE_PASSWORD";

    @Mock
    private NoteDao noteDao;
    @InjectMocks
    private RoomNoteRepository roomNoteRepository;

    private Note note;

    @BeforeEach
    void setUp() {
        note = Note.builder()
                .id(NOTE_ID)
                .title(NOTE_TITLE)
                .content(NOTE_CONTENT)
                .password(NOTE_PASSWORD)
                .build();
    }

    @Test
    void findByIdTest_noteNotExists() {
        when(noteDao.findById(anyString()))
                .thenReturn(new MutableLiveData<>(null));

        roomNoteRepository.findById(NOTE_ID).observeForever(foundNote -> {
            assertEquals(Optional.absent(), foundNote);

            verify(noteDao).findById(anyString());
        });
    }

    @Test
    void findByIdTest_noteExists() {
        when(noteDao.findById(anyString()))
                .thenReturn(new MutableLiveData<>(RoomNote.from(note)));

        roomNoteRepository.findById(NOTE_ID).observeForever(foundNote -> {
            assertEquals(Optional.of(note), foundNote);

            verify(noteDao).findById(anyString());
        });
    }

    @Test
    void findAllTest_notesNotAvailable() {
        when(noteDao.findAll())
                .thenReturn(new MutableLiveData<>(Lists.newArrayList()));

        roomNoteRepository.findAll().observeForever(foundNotes -> {
            assertThat(foundNotes, is(empty()));

            verify(noteDao).findAll();
        });
    }

    @Test
    void findAllTest_twoNotesAvailable() {
        Note anotherNote = Note.builder()
                .id(ANOTHER_NOTE_ID)
                .title(ANOTHER_NOTE_TITLE)
                .content(ANOTHER_NOTE_CONTENT)
                .password(ANOTHER_NOTE_PASSWORD)
                .build();

        when(noteDao.findAll())
                .thenReturn(new MutableLiveData<>(Lists.newArrayList(
                        RoomNote.from(note),
                        RoomNote.from(anotherNote)
                )));

        roomNoteRepository.findAll().observeForever(foundNotes -> {
            assertThat(foundNotes, containsInAnyOrder(note, anotherNote));

            verify(noteDao).findAll();
        });
    }
}