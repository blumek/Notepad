package com.blumek.notepad.adapter.repository;

import androidx.lifecycle.LiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;

import java.util.List;

public class ForwardingNoteRepository implements NoteRepository {
    final NoteRepository noteRepository;

    public ForwardingNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public LiveData<Note> findById(String id) {
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
