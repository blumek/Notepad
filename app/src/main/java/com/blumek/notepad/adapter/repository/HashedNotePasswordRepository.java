package com.blumek.notepad.adapter.repository;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.domain.port.PasswordHasher;

public class HashedNotePasswordRepository extends ForwardingNoteRepository implements NoteRepository {
    private final PasswordHasher passwordHasher;

    public HashedNotePasswordRepository(NoteRepository noteRepository, PasswordHasher passwordHasher) {
        super(noteRepository);
        this.passwordHasher = passwordHasher;
    }

    @Override
    public void create(Note note) {
        Note noteToCreate = getNoteWithHashedPassword(note);
        super.create(noteToCreate);
    }

    private Note getNoteWithHashedPassword(Note note) {
        String hashedPassword = getHashedPassword(note.getPassword());

        return note.toBuilder()
                .password(hashedPassword)
                .build();
    }

    private String getHashedPassword(String notePassword) {
        return passwordHasher.hash(notePassword);
    }

    @Override
    public void update(Note note) {
        Note noteToUpdate = getNoteWithHashedPassword(note);
        super.update(noteToUpdate);
    }
}
