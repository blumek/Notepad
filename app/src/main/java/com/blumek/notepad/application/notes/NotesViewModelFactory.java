package com.blumek.notepad.application.notes;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.usecase.FindNote;
import com.blumek.notepad.usecase.NoteAuthentication;

final class NotesViewModelFactory implements ViewModelProvider.Factory {
    private final NotesViewModel notesViewModel;

    public NotesViewModelFactory(FindNote findNote, NoteAuthentication noteAuthentication) {
        notesViewModel = new NotesViewModel(findNote, noteAuthentication);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) notesViewModel;
    }
}
