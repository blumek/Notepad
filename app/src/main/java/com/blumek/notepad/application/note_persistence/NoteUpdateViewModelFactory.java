package com.blumek.notepad.application.note_persistence;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.usecase.FindNote;
import com.blumek.notepad.usecase.UpdateNote;

final class NoteUpdateViewModelFactory implements ViewModelProvider.Factory {
    private final NoteUpdateViewModel noteUpdateViewModel;

    public NoteUpdateViewModelFactory(UpdateNote updateNote, FindNote findNote, String noteId) {
        noteUpdateViewModel = new NoteUpdateViewModel(updateNote, findNote, noteId);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) noteUpdateViewModel;
    }
}
