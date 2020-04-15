package com.blumek.notepad.application.model;

import com.blumek.notepad.domain.entity.Note;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Builder
@Value
public class ViewNote {
    String title;
    String content;
    @Getter(AccessLevel.PRIVATE)
    boolean hasPassword;

    public boolean hasPassword() {
        return hasPassword;
    }

    public static ViewNote from(Note note) {
        return ViewNote.builder()
                .title(note.getTitle())
                .content(note.getContent())
                .hasPassword(isPasswordAvailable(note))
                .build();
    }

    private static boolean isPasswordAvailable(Note note) {
        return note.getPassword() != null && !note.getPassword().isEmpty();
    }
}
