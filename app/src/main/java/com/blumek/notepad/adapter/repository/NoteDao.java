package com.blumek.notepad.adapter.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.blumek.notepad.domain.entity.Note;

import java.util.List;

interface NoteDao extends BaseDao<Note> {
    @Query("SELECT * FROM note WHERE id=:id LIMIT 1")
    LiveData<Note> findById(String id);
    @Query("SELECT * FROM note")
    LiveData<List<Note>> findAll();
}
