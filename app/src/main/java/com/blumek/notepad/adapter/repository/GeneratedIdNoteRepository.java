package com.blumek.notepad.adapter.repository;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.IdGenerator;
import com.blumek.notepad.domain.port.NoteRepository;

public class GeneratedIdNoteRepository extends ForwardingNoteRepository implements NoteRepository {
    private final IdGenerator idGenerator;

    public GeneratedIdNoteRepository(NoteRepository noteRepository, IdGenerator idGenerator) {
        super(noteRepository);
        this.idGenerator = idGenerator;
    }

    @Override
    public void create(Note note) {
        Note noteToCreate = Note.builder()
                .id(idGenerator.generate())
                .title(note.getTitle())
                .content(note.getContent())
                .password(note.getPassword())
                .build();

        super.create(noteToCreate);
    }

}
