package com.blumek.notepad.application.config;

import androidx.lifecycle.ViewModel;

import com.blumek.notepad.application.view_model.NotesViewModel;
import com.blumek.notepad.application.view_model.factory.ViewModelFactory;
import com.blumek.notepad.usecase.FindNote;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
class ViewModelModule {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(NotesViewModel.class)
    ViewModel notesViewModel(FindNote findNote) {
        return new NotesViewModel(findNote);
    }
}
