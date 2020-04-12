package com.blumek.notepad.adapter.repository;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentEncoder;
import com.blumek.notepad.domain.port.NoteRepository;

public class EncodedNoteContentRepository extends ForwardingNoteRepository implements NoteRepository {
    private final NoteContentEncoder noteContentEncoder;

    public EncodedNoteContentRepository(NoteRepository noteRepository, NoteContentEncoder noteContentEncoder) {
        super(noteRepository);
        this.noteContentEncoder = noteContentEncoder;
    }

    @Override
    public void create(Note note) {
        super.create(noteContentEncoder.encodeContent(note));
    }

    @Override
    public void update(Note note) {
        super.update(noteContentEncoder.encodeContent(note));
    }
}
