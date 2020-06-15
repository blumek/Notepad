package com.blumek.notepad.application.notes;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.usecase.FindNote;

final class NotesViewModelFactory implements ViewModelProvider.Factory {
    private final NotesViewModel notesViewModel;

    public NotesViewModelFactory(final FindNote findNote) {
        notesViewModel = new NotesViewModel(findNote);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) notesViewModel;
    }
}
