package com.blumek.notepad.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.domain.port.PasswordHasher;
import com.google.common.base.Optional;

public final class UnlockNote {
    private final NoteRepository noteRepository;
    private final PasswordHasher passwordHasher;

    public UnlockNote(NoteRepository noteRepository, PasswordHasher passwordHasher) {
        this.noteRepository = noteRepository;
        this.passwordHasher = passwordHasher;
    }

    public LiveData<Optional<Note>> unlockNote(String id, String password) {
        return Transformations.map(noteRepository.findById(id),
                note -> authenticateNote(note, password));
    }

    private Optional<Note> authenticateNote(Optional<Note> note, String password) {
        if (!note.isPresent())
            return Optional.absent();

        Note foundNote = note.get();
        String hashedPassword = passwordHasher.hash(password);
        if (isNoteUnlocked(foundNote, hashedPassword))
            return note;

        return Optional.absent();
    }

    private boolean isNoteUnlocked(Note foundNote, String hashedPassword) {
        return foundNote.getPassword() == null || foundNote.getPassword().equals(hashedPassword);
    }
}
