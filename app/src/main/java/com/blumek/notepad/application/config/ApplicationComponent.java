package com.blumek.notepad.application.config;

import android.app.Application;

import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.application.NotepadApplication;
import com.blumek.notepad.application.database.AppDatabase;
import com.blumek.notepad.application.fragment.NotesFragment;
import com.blumek.notepad.application.view_model.factory.ViewModelFactory;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.usecase.FindNote;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        RoomModule.class,
        ViewModelModule.class
})
public interface ApplicationComponent {
    Application application();

    void inject(NotepadApplication notepadApplication);

    void inject(NotesFragment notesFragment);

    AppDatabase database();

    NoteDao noteDao();

    NoteRepository noteRepository();

    FindNote findNote();

    ViewModelFactory viewModelFactory();
}
