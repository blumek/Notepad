package com.blumek.notepad.application.change_note_password;

import lombok.Data;

@Data
public final class ChangePassword {
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirmation;

    public static ChangePassword empty() {
        return new ChangePassword();
    }
}
