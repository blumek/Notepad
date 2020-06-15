package com.blumek.notepad.application.note_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.usecase.FindNote;

import java.util.Optional;

import lombok.Getter;

@Getter
final class NoteDetailsViewModel extends ViewModel {
    private final LiveData<Optional<Note>> note;

    public NoteDetailsViewModel(FindNote findNote, String id) {
        this.note = findNote.findById(id);
    }
}