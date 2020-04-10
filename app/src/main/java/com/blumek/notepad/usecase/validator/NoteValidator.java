package com.blumek.notepad.usecase.validator;

import com.blumek.notepad.domain.entity.Note;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoteValidator {
    public static boolean isValid(Note note) {
        return true;
    }
}
