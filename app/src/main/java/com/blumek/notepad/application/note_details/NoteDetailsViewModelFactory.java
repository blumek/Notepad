package com.blumek.notepad.application.note_details;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.usecase.FindNote;

final class NoteDetailsViewModelFactory implements ViewModelProvider.Factory {
    private final NoteDetailsViewModel noteDetailsViewModel;

    public NoteDetailsViewModelFactory(FindNote findNote, String id) {
        noteDetailsViewModel = new NoteDetailsViewModel(findNote, id);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) noteDetailsViewModel;
    }
}
