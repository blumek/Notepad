package com.blumek.notepad.application.config;

import com.blumek.notepad.application.fragment.NotesFragment;
import com.blumek.notepad.application.view_model.factory.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        FindNoteModule.class,
        RoomModule.class,
        ViewModelModule.class
})
public interface ApplicationComponent {
    void inject(NotesFragment notesFragment);

    ViewModelFactory viewModelFactory();
}
