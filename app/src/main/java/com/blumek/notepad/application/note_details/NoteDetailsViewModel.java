package com.blumek.notepad.application.note_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

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
}