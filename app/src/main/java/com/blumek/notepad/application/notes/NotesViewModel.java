package com.blumek.notepad.application.notes;

import android.view.View;

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

final class NotesViewModel extends ViewModel {
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
}
