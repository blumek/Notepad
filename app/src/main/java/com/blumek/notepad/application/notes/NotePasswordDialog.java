package com.blumek.notepad.application.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.blumek.notepad.R;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class NotePasswordDialog extends DialogFragment {
    private final InputListener listener;

    public interface InputListener {
        void onPasswordSubmit(String password);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View inflatedView = inflater.inflate(R.layout.password_dialog, null);
        EditText passwordEditText = inflatedView.findViewById(R.id.password);

        dialogBuilder
                .setView(inflatedView)
                .setPositiveButton("Unlock", (dialog, id) ->
                        listener.onPasswordSubmit(passwordEditText.getText().toString()))
                .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

        return dialogBuilder.create();
    }
}
