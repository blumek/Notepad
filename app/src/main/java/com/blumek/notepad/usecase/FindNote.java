package com.blumek.notepad.usecase;

import androidx.lifecycle.LiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;

import java.util.List;

public final class FindNote {
    private final NoteRepository noteRepository;

    public FindNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public LiveData<List<Note>> findAll() {
        return noteRepository.findAll();
    }
}
