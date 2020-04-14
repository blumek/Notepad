package com.blumek.notepad.application.view_model.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.application.view_model.NotesViewModel;
import com.blumek.notepad.usecase.FindNote;

public class NotesViewModelFactory implements ViewModelProvider.Factory {
    private final FindNote findNote;

    public NotesViewModelFactory(FindNote findNote) {
        this.findNote = findNote;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NotesViewModel(findNote);
    }
}
