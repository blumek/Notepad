package com.blumek.notepad.application.notes;

import com.blumek.notepad.domain.entity.Note;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Builder
@Value
public class ViewNoteShort {

    String id;
    String title;
    @Getter(AccessLevel.PRIVATE)
    boolean hasPassword;

    public boolean hasPassword() {
        return hasPassword;
    }

    public static ViewNoteShort from(Note note) {
        return ViewNoteShort.builder()
                .id(note.getId())
                .title(note.getTitle())
                .hasPassword(isPasswordAvailable(note))
                .build();
    }

    private static boolean isPasswordAvailable(Note note) {
        return note.getPassword() != null && !note.getPassword().isEmpty();
    }
}
