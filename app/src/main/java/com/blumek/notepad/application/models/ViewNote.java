package com.blumek.notepad.application.models;

import com.blumek.notepad.domain.entity.Note;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ViewNote {
    String title;
    String content;

    public static ViewNote from(Note note) {
        return ViewNote.builder()
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }
}
