package com.blumek.notepad.adapter.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentDecoder;
import com.blumek.notepad.domain.port.NoteRepository;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

public class DecodedNoteContentRepository extends ForwardingNoteRepository implements NoteRepository {
    private final NoteContentDecoder noteContentDecoder;

    public DecodedNoteContentRepository(NoteRepository noteRepository,
                                        NoteContentDecoder noteContentDecoder) {
        super(noteRepository);
        this.noteContentDecoder = noteContentDecoder;
    }

    @Override
    public LiveData<Optional<Note>> findById(String id) {
        return Transformations.map(super.findById(id), note ->
                note.transform(noteContentDecoder::decodeContent));
    }

    @Override
    public LiveData<List<Note>> findAll() {
        return Transformations.map(super.findAll(), this::decodeNotesContents);
    }

    private List<Note> decodeNotesContents(List<Note> notes) {
        List<Note> decodedNotes = Lists.newArrayList();
        for (Note note : notes)
            decodedNotes.add(noteContentDecoder.decodeContent(note));
        return decodedNotes;
    }
}
