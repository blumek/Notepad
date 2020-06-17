package com.blumek.notepad.application.note_persistence;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.usecase.CreateNote;

final class NoteCreationViewModelFactory implements ViewModelProvider.Factory {
    private final NoteCreationViewModel noteCreationViewModel;

    public NoteCreationViewModelFactory(CreateNote createNote) {
        noteCreationViewModel = new NoteCreationViewModel(createNote);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) noteCreationViewModel;
    }
}
