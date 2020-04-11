package com.blumek.notepad.adapter.repository;

import androidx.lifecycle.LiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.google.common.base.Optional;

import java.util.List;

abstract class ForwardingNoteRepository implements NoteRepository {
    private final NoteRepository noteRepository;

    ForwardingNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public LiveData<Optional<Note>> findById(String id) {
        return noteRepository.findById(id);
    }

    @Override
    public LiveData<List<Note>> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public void create(Note note) {
        noteRepository.create(note);
    }

    @Override
    public void update(Note note) {
        noteRepository.update(note);
    }

    @Override
    public void delete(Note note) {
        noteRepository.delete(note);
    }
}
