package com.blumek.notepad.application.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.adapter.repository.model.RoomNote;

@Database(entities = RoomNote.class, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "app_database";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract NoteDao noteDao();
}