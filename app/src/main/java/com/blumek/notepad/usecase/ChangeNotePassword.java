package com.blumek.notepad.usecase;

import com.blumek.notepad.domain.entity.Note;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ChangeNotePassword {
    private final UpdateNote updateNote;
    private final FindNote findNote;

    public void changeById(String noteId, String password) {
        findNote.findById(noteId).observeForever(foundNote -> {
            if (!foundNote.isPresent())
                throw new IllegalArgumentException();

            Note noteToUpdate = foundNote.get()
                    .toBuilder()
                    .password(password)
                    .build();

            updateNote.update(noteToUpdate);
        });
    }
}
