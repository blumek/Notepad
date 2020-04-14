package com.blumek.notepad.application.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.blumek.notepad.application.model.ViewNote;
import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.usecase.FindNote;
import com.google.common.collect.Lists;

import java.util.List;

public class NotesViewModel extends ViewModel {
    private final LiveData<List<Note>> notes;

    public NotesViewModel(FindNote findNote) {
        notes = findNote.findAll();
    }

    public LiveData<List<ViewNote>> getViewNotes() {
        return Transformations.map(notes, this::toViewNotes);
    }

    private List<ViewNote> toViewNotes(List<Note> notes) {
        List<ViewNote> viewNotes = Lists.newArrayList();
        for (Note note : notes)
            viewNotes.add(ViewNote.from(note));
        return viewNotes;
    }
}
