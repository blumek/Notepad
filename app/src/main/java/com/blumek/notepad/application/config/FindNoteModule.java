package com.blumek.notepad.application.config;

import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.usecase.FindNote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class FindNoteModule {
    @Singleton
    @Provides
    FindNote providesFindNote(NoteRepository noteRepository) {
        return new FindNote(noteRepository);
    }
}
