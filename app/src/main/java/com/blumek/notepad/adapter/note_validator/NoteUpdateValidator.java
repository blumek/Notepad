package com.blumek.notepad.adapter.note_validator;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteValidator;

public class NoteUpdateValidator implements NoteValidator {
    @Override
    public boolean isValid(Note note) {
        return note != null &&
                hasId(note) &&
                hasTitle(note) &&
                hasContent(note);
    }

    private boolean hasId(Note note) {
        return note.getId() != null && !note.getId().isEmpty();
    }

    private boolean hasTitle(Note note) {
        return note.getTitle() != null && !note.getTitle().isEmpty();
    }

    private boolean hasContent(Note note) {
        return note.getContent() != null && !note.getContent().isEmpty();
    }
}
