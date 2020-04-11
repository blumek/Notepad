package com.blumek.notepad.adapter.repository;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteEncoder;
import com.blumek.notepad.domain.port.NoteRepository;

public class EncodedNoteRepository extends ForwardingNoteRepository implements NoteRepository {
    private final NoteEncoder noteEncoder;

    public EncodedNoteRepository(NoteRepository noteRepository, NoteEncoder noteEncoder) {
        super(noteRepository);
        this.noteEncoder = noteEncoder;
    }

    @Override
    public void create(Note note) {
        super.create(noteEncoder.encode(note));
    }

    @Override
    public void update(Note note) {
        super.update(noteEncoder.encode(note));
    }
}
