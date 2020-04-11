package com.blumek.notepad.domain.port;

import androidx.lifecycle.LiveData;

import com.blumek.notepad.domain.entity.Note;
import com.google.common.base.Optional;

import java.util.List;

public interface NoteRepository {
    LiveData<Optional<Note>> findById(String id);
    LiveData<List<Note>> findAll();
    void create(Note note);
    void update(Note note);
    void delete(Note note);
}
