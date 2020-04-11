package com.blumek.notepad.adapter.repository;

import androidx.lifecycle.MutableLiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentDecoder;
import com.blumek.notepad.domain.port.NoteRepository;
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
class DecodedNoteContentRepositoryTest {
    private static final String NOTE_ID = "NOTE_ID";
    private static final String ENCODED_NOTE_CONTENT = "ENCODED_NOTE_CONTENT";
    private static final String DECODED_NOTE_CONTENT = "DECODED_NOTE_CONTENT";
    private static final String SECOND_ENCODED_NOTE_CONTENT = "SECOND_ENCODED_NOTE_CONTENT";
    private static final String SECOND_DECODED_NOTE_CONTENT = "SECOND_DECODED_NOTE_CONTENT";

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NoteContentDecoder noteContentDecoder;
    @InjectMocks
    private DecodedNoteContentRepository decodedNoteContentRepository;

    private Note note;
    private Note decodedContentNote;

    @BeforeEach
    void setUp() {
        note = Note.builder()
                .content(ENCODED_NOTE_CONTENT)
                .build();

        decodedContentNote = note.toBuilder()
                .content(DECODED_NOTE_CONTENT)
                .build();
    }

    @Test
    void findByIdTest_noteNotExists() {
        when(noteRepository.findById(anyString()))
                .thenReturn(new MutableLiveData<>(Optional.absent()));

        decodedNoteContentRepository.findById(NOTE_ID).observeForever(foundNote -> {
            assertEquals(Optional.absent(), foundNote);

            verify(noteRepository).findById(anyString());
        });
    }

    @Test
    void findByIdTest_noteExists() {
        when(noteRepository.findById(anyString()))
                .thenReturn(new MutableLiveData<>(Optional.of(note)));

        when(noteContentDecoder.decodeContent(note))
                .thenReturn(decodedContentNote);

        decodedNoteContentRepository.findById(NOTE_ID).observeForever(foundNote -> {
            assertEquals(Optional.of(decodedContentNote), foundNote);

            verify(noteContentDecoder).decodeContent(note);
            verify(noteRepository).findById(anyString());
        });
    }

    @Test
    void findAllTest_notesNotAvailable() {
        when(noteRepository.findAll())
                .thenReturn(new MutableLiveData<>(Lists.newArrayList()));

        decodedNoteContentRepository.findAll().observeForever(foundNotes -> {
            assertThat(foundNotes, is(empty()));

            verify(noteRepository).findAll();
        });
    }

    @Test
    void findAllTest_twoNotesAvailable() {
        Note secondNote = Note.builder()
                .content(SECOND_ENCODED_NOTE_CONTENT)
                .build();

        Note secondDecodedContentNote = secondNote.toBuilder()
                .content(SECOND_DECODED_NOTE_CONTENT)
                .build();

        when(noteRepository.findAll())
                .thenReturn(new MutableLiveData<>(Lists.newArrayList(note, secondNote)));

        when(noteContentDecoder.decodeContent(note))
                .thenReturn(decodedContentNote);

        when(noteContentDecoder.decodeContent(secondNote))
                .thenReturn(secondDecodedContentNote);

        decodedNoteContentRepository.findAll().observeForever(foundNotes -> {
            assertThat(foundNotes,
                    containsInAnyOrder(decodedContentNote, secondDecodedContentNote));

            verify(noteRepository).findAll();
            verify(noteContentDecoder).decodeContent(note);
            verify(noteContentDecoder).decodeContent(secondNote);
        });
    }
}