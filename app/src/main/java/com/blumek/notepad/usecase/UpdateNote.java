package com.blumek.notepad.usecase;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.exception.InvalidNoteException;
import com.blumek.notepad.domain.port.NoteEncoder;
import com.blumek.notepad.domain.port.NoteRepository;

import static com.blumek.notepad.usecase.validator.NoteValidator.isValid;

public final class UpdateNote {
    private final NoteRepository noteRepository;
    private final NoteEncoder noteEncoder;

    public UpdateNote(NoteRepository noteRepository, NoteEncoder noteEncoder) {
        this.noteRepository = noteRepository;
        this.noteEncoder = noteEncoder;
    }

    public Note update(Note note) {
        if (!isValid(note) || !hasId(note))
            throw new InvalidNoteException();

        Note encodedNote = noteEncoder.encode(note);
        noteRepository.update(encodedNote);

        return encodedNote.toBuilder()
                .content(note.getContent())
                .build();
    }

    private boolean hasId(Note note) {
        return note.getId() != null;
    }
}
