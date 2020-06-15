package com.blumek.notepad.application.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.usecase.FindNote;

import java.util.List;

import static java.util.stream.Collectors.toList;

final class NotesViewModel extends ViewModel {
    private final LiveData<List<Note>> notes;

    public NotesViewModel(FindNote findNote) {
        notes = findNote.findAll();
    }

    public LiveData<List<ViewNoteShort>> getViewNotes() {
        return Transformations.map(notes, this::mapToViewNotes);
    }

    private List<ViewNoteShort> mapToViewNotes(List<Note> notes) {
        return notes.stream()
                .map(ViewNoteShort::from)
                .collect(toList());
    }
}
