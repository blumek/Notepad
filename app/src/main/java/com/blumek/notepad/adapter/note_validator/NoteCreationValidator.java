package com.blumek.notepad.adapter.note_validator;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteValidator;

public class NoteCreationValidator implements NoteValidator {
    @Override
    public boolean isValid(Note note) {
        return note != null &&
                hasTitle(note) &&
                hasContent(note);
    }

    private boolean hasTitle(Note note) {
        return note.getTitle() != null && !note.getTitle().isEmpty();
    }

    private boolean hasContent(Note note) {
        return note.getContent() != null && !note.getContent().isEmpty();
    }
}
