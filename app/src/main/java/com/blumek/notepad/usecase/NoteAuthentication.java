package com.blumek.notepad.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.domain.port.PasswordValidator;

import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public final class NoteAuthentication {
    private final NoteRepository noteRepository;
    private final PasswordValidator passwordValidator;

    public LiveData<Boolean> authenticateWithPassword(String id, String password) {
        return Transformations.map(noteRepository.findById(id),
                note -> authenticateNote(note.orElseThrow(() ->
                        new IllegalArgumentException(format("Note with ID: %s not found", id))),
                        password));
    }

    private boolean authenticateNote(Note note, String password) {
        if (note.getPassword() == null)
            return true;

        return passwordValidator.validate(password, note.getPassword());
    }
}
