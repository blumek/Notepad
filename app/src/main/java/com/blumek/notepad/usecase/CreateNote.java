package com.blumek.notepad.usecase;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.exception.InvalidNoteException;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.domain.port.NoteValidator;


public final class CreateNote {
    private final NoteRepository noteRepository;
    private final NoteValidator noteValidator;

    public CreateNote(NoteRepository noteRepository, NoteValidator noteValidator) {
        this.noteRepository = noteRepository;
        this.noteValidator = noteValidator;
    }

    public void create(Note note) {
        if (!noteValidator.isValid(note))
            throw new InvalidNoteException();

        noteRepository.create(note);
    }
}
