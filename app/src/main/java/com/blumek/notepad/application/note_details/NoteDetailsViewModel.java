package com.blumek.notepad.application.note_details;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.usecase.FindNote;

import lombok.Getter;

@Getter
public final class NoteDetailsViewModel extends ViewModel {
    private final LiveData<Note> note;

    public NoteDetailsViewModel(FindNote findNote, String id) {
        this.note = Transformations.map(findNote.findById(id),
                foundNote -> foundNote.orElseThrow(IllegalArgumentException::new));
    }

    public void openNoteUpdate(View view, String noteId) {
        NavDirections direction = NoteDetailsFragmentDirections
                .actionNoteDetailsFragmentToNoteUpdateFragment(noteId);

        Navigation.findNavController(view).navigate(direction);
    }

    public void openChangeNotePassword(View view, String noteId) {
        NavDirections direction = NoteDetailsFragmentDirections
                .actionNoteDetailsFragmentToChangeNotePasswordFragment(noteId);

        Navigation.findNavController(view).navigate(direction);
    }
}