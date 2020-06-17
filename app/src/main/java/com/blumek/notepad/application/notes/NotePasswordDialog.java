package com.blumek.notepad.application.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.blumek.notepad.R;

public class NotePasswordDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        dialogBuilder
                .setView(inflater.inflate(R.layout.password_dialog, null))
                .setPositiveButton("Unlock", (dialog, id) -> {
                    // unlock note
                })
                .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

        return dialogBuilder.create();
    }
}
