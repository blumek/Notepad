package com.blumek.notepad.application.change_note_password;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.domain.port.PasswordHasher;
import com.blumek.notepad.usecase.FindNote;
import com.blumek.notepad.usecase.UpdateNote;

final class ChangeNotePasswordViewModelFactory implements ViewModelProvider.Factory {
    private final ChangeNotePasswordViewModel changeNotePasswordViewModel;

    public ChangeNotePasswordViewModelFactory(UpdateNote updateNote, FindNote findNote, String id,
                                              PasswordHasher passwordHasher) {
        changeNotePasswordViewModel =
                new ChangeNotePasswordViewModel(updateNote, findNote, id, passwordHasher);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) changeNotePasswordViewModel;
    }
}
