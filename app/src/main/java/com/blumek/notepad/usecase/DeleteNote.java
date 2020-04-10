package com.blumek.notepad.usecase;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;

public final class DeleteNote {
    private final NoteRepository noteRepository;

    public DeleteNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }
}
