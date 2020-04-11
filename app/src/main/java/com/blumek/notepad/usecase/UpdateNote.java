package com.blumek.notepad.usecase;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.exception.InvalidNoteException;
import com.blumek.notepad.domain.port.NoteRepository;

import static com.blumek.notepad.usecase.validator.NoteValidator.isValid;

public final class UpdateNote {
    private final NoteRepository noteRepository;

    public UpdateNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void update(Note note) {
        if (!isValid(note) || !hasId(note))
            throw new InvalidNoteException();

        noteRepository.update(note);
    }

    private boolean hasId(Note note) {
        return note.getId() != null;
    }
}
