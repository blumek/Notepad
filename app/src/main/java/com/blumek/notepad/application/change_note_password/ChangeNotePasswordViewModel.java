package com.blumek.notepad.application.change_note_password;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.PasswordHasher;
import com.blumek.notepad.usecase.FindNote;
import com.blumek.notepad.usecase.UpdateNote;

public final class ChangeNotePasswordViewModel extends ViewModel {
    private final UpdateNote updateNote;
    private final PasswordHasher passwordHasher;
    private final MutableLiveData<Note> note;
    private final MutableLiveData<ChangePassword> changePassword;

    public ChangeNotePasswordViewModel(UpdateNote updateNote, FindNote findNote, String noteId,
                                       PasswordHasher passwordHasher) {
        this.updateNote = updateNote;
        this.passwordHasher = passwordHasher;
        this.changePassword = new MutableLiveData<>(ChangePassword.empty());
        this.note = new MutableLiveData<>();
        findNote.findById(noteId).observeForever(foundNote -> {
            if (foundNote.isPresent()) {
                note.postValue(foundNote.get());
            } else
                throw new IllegalArgumentException();
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

        if (!providedChangePassword.getNewPassword()
                .equals(providedChangePassword.getNewPasswordConfirmation()))
            return;

        if (noteToChangePassword.getPassword() == null &&
                providedChangePassword.getOldPassword() == null)
            changePassword(noteToChangePassword, providedChangePassword);

        String providedOldPassword = providedChangePassword.getOldPassword();
        if (providedOldPassword == null)
            return;

        String hashedProvidedOldPassword = passwordHasher.hash(providedOldPassword);

        if (hashedProvidedOldPassword.equals(noteToChangePassword.getPassword())) {
            changePassword(noteToChangePassword, providedChangePassword);
        }
    }

    private void changePassword(Note note, ChangePassword changePassword) {
        Note noteToUpdate = note.toBuilder()
                .password(changePassword.getNewPassword())
                .build();

        updateNote.update(noteToUpdate);
    }

    public LiveData<ChangePassword> getChangePassword() {
        return changePassword;
    }
}