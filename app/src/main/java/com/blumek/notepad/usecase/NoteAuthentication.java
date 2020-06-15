package com.blumek.notepad.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.domain.port.PasswordHasher;

import static java.lang.String.format;

public final class NoteAuthentication {
    private final NoteRepository noteRepository;
    private final PasswordHasher passwordHasher;

    public NoteAuthentication(NoteRepository noteRepository, PasswordHasher passwordHasher) {
        this.noteRepository = noteRepository;
        this.passwordHasher = passwordHasher;
    }

    public LiveData<Boolean> authenticateWithPassword(String id, String password) {
        return Transformations.map(noteRepository.findById(id),
                note -> authenticateNote(note.orElseThrow(() ->
                        new IllegalArgumentException(format("Note with ID: %s not found", id))),
                        password));
    }

    private boolean authenticateNote(Note note, String password) {
        String hashedPassword = passwordHasher.hash(password);
        return isNoteAuthenticated(note, hashedPassword);
    }

    private boolean isNoteAuthenticated(Note foundNote, String hashedPassword) {
        return foundNote.getPassword() == null || foundNote.getPassword().equals(hashedPassword);
    }
}
