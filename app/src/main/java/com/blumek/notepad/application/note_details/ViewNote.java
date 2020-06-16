package com.blumek.notepad.application.note_details;

import com.blumek.notepad.domain.entity.Note;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
class ViewNote {
    String id;
    String title;
    String content;

    public static ViewNote from(Note note) {
        return ViewNote.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }
}
