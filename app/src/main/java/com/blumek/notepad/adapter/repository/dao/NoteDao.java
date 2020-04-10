package com.blumek.notepad.adapter.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.blumek.notepad.adapter.repository.model.RoomNote;

import java.util.List;

@Dao
public interface NoteDao extends BaseDao<RoomNote> {
    @Query("SELECT * FROM RoomNote WHERE id=:id LIMIT 1")
    LiveData<RoomNote> findById(String id);
    @Query("SELECT * FROM RoomNote")
    LiveData<List<RoomNote>> findAll();
}
