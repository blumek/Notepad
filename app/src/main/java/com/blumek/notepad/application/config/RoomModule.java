package com.blumek.notepad.application.config;

import com.blumek.notepad.adapter.repository.RoomNoteRepository;
import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.application.database.AppDatabase;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.usecase.FindNote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private final AppDatabase database;

    public RoomModule(AppDatabase database) {
        this.database = database;
    }

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase() {
        return database;
    }

    @Singleton
    @Provides
    NoteDao providesNoteDao(AppDatabase database) {
        return database.noteDao();
    }

    @Singleton
    @Provides
    NoteRepository providesNoteRepository(NoteDao noteDao) {
        return new RoomNoteRepository(noteDao);
    }

    @Singleton
    @Provides
    FindNote providesFindNote(NoteRepository noteRepository) {
        return new FindNote(noteRepository);
    }
}
