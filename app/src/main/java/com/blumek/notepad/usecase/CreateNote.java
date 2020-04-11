package com.blumek.notepad.usecase;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.exception.InvalidNoteException;
import com.blumek.notepad.domain.port.NoteRepository;

import static com.blumek.notepad.usecase.validator.NoteValidator.isValid;

public final class CreateNote {
    private final NoteRepository noteRepository;

    public CreateNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void create(Note note) {
        if (!isValid(note))
            throw new InvalidNoteException();

        noteRepository.create(note);
    }
}
