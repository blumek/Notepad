package com.blumek.notepad.adapter.repository.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

interface BaseDao<T> {
    @Insert
    void save(T entity);

    @Update
    void update(T entity);

    @Delete
    void delete(T entity);
}