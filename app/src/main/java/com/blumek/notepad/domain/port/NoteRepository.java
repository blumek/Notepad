package com.blumek.notepad.domain.port;

import androidx.lifecycle.LiveData;

import com.blumek.notepad.domain.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {
    LiveData<Optional<Note>> findById(String id);
    LiveData<List<Note>> findAll();
    void create(Note note);
    void update(Note note);
    void delete(Note note);
}
