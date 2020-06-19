package com.blumek.notepad.application.change_note_password;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.domain.port.PasswordHasher;
import com.blumek.notepad.usecase.ChangeNotePassword;
import com.blumek.notepad.usecase.FindNote;

final class ChangeNotePasswordViewModelFactory implements ViewModelProvider.Factory {
    private final ChangeNotePasswordViewModel changeNotePasswordViewModel;

    public ChangeNotePasswordViewModelFactory(ChangeNotePassword changeNotePassword,
                                              FindNote findNote, String id,
                                              PasswordHasher passwordHasher) {
        changeNotePasswordViewModel =
                new ChangeNotePasswordViewModel(changeNotePassword, findNote, id, passwordHasher);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) changeNotePasswordViewModel;
    }
}
