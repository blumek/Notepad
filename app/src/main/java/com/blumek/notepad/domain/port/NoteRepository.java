package com.blumek.notepad.domain.port;

import androidx.lifecycle.LiveData;

import com.blumek.notepad.domain.entity.Note;

import org.w3c.dom.Node;

import java.util.List;

public interface NoteRepository {
    LiveData<Note> findById(String id);
    LiveData<List<Node>> findAll();
    LiveData<Note> create(Note note);
    LiveData<Note> update(Note note);
    void delete(Note note);
}
