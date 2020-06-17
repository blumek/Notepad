package com.blumek.notepad.application.notes;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.usecase.FindNote;
import com.blumek.notepad.usecase.NoteAuthentication;

import java.util.List;

import static java.util.stream.Collectors.toList;

public final class NotesViewModel extends ViewModel {
    private final NoteAuthentication noteAuthentication;
    private final LiveData<List<Note>> notes;

    public NotesViewModel(FindNote findNote, NoteAuthentication noteAuthentication) {
        this.notes = findNote.findAll();
        this.noteAuthentication = noteAuthentication;
    }

    public LiveData<List<ViewNoteShort>> getViewNotes() {
        return Transformations.map(notes, this::mapToViewNotes);
    }

    private List<ViewNoteShort> mapToViewNotes(List<Note> notes) {
        return notes.stream()
                .map(ViewNoteShort::from)
                .collect(toList());
    }

    public LiveData<Boolean> authenticateWithPassword(String id, String password) {
        return noteAuthentication.authenticateWithPassword(id, password);
    }

    public void openNoteDetails(View view, ViewNoteShort noteShort) {
        NavDirections direction = NotesFragmentDirections
                .actionNotesFragmentToNoteDetailsFragment(noteShort.getId());

        Navigation.findNavController(view).navigate(direction);
    }

    public void openNoteCreation(View view) {
        NavDirections direction = NotesFragmentDirections
                .actionNotesFragmentToNoteCreationFragment();

        Navigation.findNavController(view).navigate(direction);
    }
}
