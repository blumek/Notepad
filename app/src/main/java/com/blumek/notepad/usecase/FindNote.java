package com.blumek.notepad.usecase;

import androidx.lifecycle.LiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;

import java.util.List;
import java.util.Optional;

public final class FindNote {
    private final NoteRepository noteRepository;

    public FindNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public LiveData<Optional<Note>> findById(String id) {
        return noteRepository.findById(id);
    }

    public LiveData<List<Note>> findAll() {
        return noteRepository.findAll();
    }
}
