package com.blumek.notepad.application.note_persistence;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.usecase.CreateNote;

import lombok.Getter;

@Getter
public final class NoteCreationViewModel extends ViewModel {
    private final MutableLiveData<PersistenceNote> note;
    private final CreateNote createNote;

    public NoteCreationViewModel(CreateNote createNote) {
        this.createNote = createNote;
        note = new MutableLiveData<>(PersistenceNote.empty());
    }

    public void onSaveClick(View view) {
        saveNote();
        Navigation.findNavController(view).navigateUp();
    }

    private void saveNote() {
        PersistenceNote persistenceNote = note.getValue();
        if (persistenceNote == null)
            return;

        Note noteToCreate = persistenceNote.toNote();
        createNote.create(noteToCreate);
    }
}