package com.blumek.notepad.application.change_note_password;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.PasswordValidator;
import com.blumek.notepad.usecase.ChangeNotePassword;
import com.blumek.notepad.usecase.FindNote;

public final class ChangeNotePasswordViewModel extends ViewModel {
    private final PasswordValidator passwordValidator;
    private final MutableLiveData<Note> note;
    private final MutableLiveData<ChangePassword> changePassword;
    private final ChangeNotePassword changeNotePassword;

    public ChangeNotePasswordViewModel(ChangeNotePassword changeNotePassword, FindNote findNote,
                                       String noteId, PasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
        this.changeNotePassword = changeNotePassword;
        this.changePassword = new MutableLiveData<>(ChangePassword.empty());
        this.note = new MutableLiveData<>();
        findNote.findById(noteId).observeForever(foundNote -> {
            if (!foundNote.isPresent()) {
                throw new IllegalArgumentException();
            }

            note.postValue(foundNote.get());
        });
    }

    public void onChangeClick(View view) {
        changePassword();
        Navigation.findNavController(view).navigateUp();
    }

    private void changePassword() {
        Note noteToChangePassword = note.getValue();
        ChangePassword providedChangePassword = changePassword.getValue();

        if (noteToChangePassword == null || providedChangePassword == null)
            throw new NullPointerException();

        String newPassword = providedChangePassword.getNewPassword();
        String newPasswordConfirmation = providedChangePassword.getNewPasswordConfirmation();
        if (!newPassword.equals(newPasswordConfirmation))
            return;

        if (isNoteWithoutPassword(noteToChangePassword, providedChangePassword.getOldPassword()))
            changeNotePassword.changeById(noteToChangePassword.getId(), newPassword);

        String providedOldPassword = providedChangePassword.getOldPassword();
        if (providedOldPassword == null)
            return;

        if (passwordValidator.validate(providedOldPassword, noteToChangePassword.getPassword()))
            changeNotePassword.changeById(noteToChangePassword.getId(), newPassword);
    }

    private boolean isNoteWithoutPassword(Note noteToChangePassword, String password) {
        return noteToChangePassword.getPassword() == null && password == null;
    }

    public LiveData<ChangePassword> getChangePassword() {
        return changePassword;
    }
}