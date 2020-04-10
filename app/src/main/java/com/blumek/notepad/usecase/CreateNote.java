package com.blumek.notepad.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.exception.InvalidNoteException;
import com.blumek.notepad.domain.port.IdGenerator;
import com.blumek.notepad.domain.port.NoteEncoder;
import com.blumek.notepad.domain.port.NoteRepository;

import static com.blumek.notepad.usecase.validator.NoteValidator.isValid;

public class CreateNote {
    private final NoteRepository noteRepository;
    private final IdGenerator idGenerator;
    private final NoteEncoder noteEncoder;

    public CreateNote(NoteRepository noteRepository, IdGenerator idGenerator,
                      NoteEncoder noteEncoder) {
        this.noteRepository = noteRepository;
        this.idGenerator = idGenerator;
        this.noteEncoder = noteEncoder;
    }

    public Note create(Note note) {
        if (!isValid(note))
            throw new InvalidNoteException();

        Note noteToCreate = Note.builder()
                .id(idGenerator.generate())
                .title(note.getTitle())
                .content(note.getContent())
                .password(note.getPassword())
                .build();

        Note encodedNote = noteEncoder.encode(noteToCreate);
        noteRepository.create(encodedNote);

        return noteToCreate.toBuilder()
                .password(encodedNote.getPassword())
                .build();
    }
}
