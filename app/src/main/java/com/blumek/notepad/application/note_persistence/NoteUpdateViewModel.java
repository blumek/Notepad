package com.blumek.notepad.application.note_persistence;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.usecase.FindNote;
import com.blumek.notepad.usecase.UpdateNote;

import lombok.Getter;

@Getter
public final class NoteUpdateViewModel extends ViewModel {
    private final MutableLiveData<PersistenceNote> note;
    private final UpdateNote updateNote;

    public NoteUpdateViewModel(UpdateNote updateNote, FindNote findNote, String noteId) {
        this.updateNote = updateNote;
        note = new MutableLiveData<>(PersistenceNote.empty());
        findNote.findById(noteId).observeForever(foundNote -> {
            if (foundNote.isPresent()) {
                PersistenceNote persistenceNote = PersistenceNote.from(foundNote.get());
                note.postValue(persistenceNote);
            } else
                throw new IllegalArgumentException();
        });
    }

    public void onUpdateClick(View view) {
        updateNote();
        Navigation.findNavController(view).navigateUp();
    }

    private void updateNote() {
        PersistenceNote persistenceNote = note.getValue();
        if (persistenceNote == null)
            return;

        Note noteToUpdate = persistenceNote.toNote();
        updateNote.update(noteToUpdate);
    }
}