package com.blumek.notepad.application.note_creation;

import com.blumek.notepad.domain.entity.Note;

import lombok.Data;

@Data
public final class PersistenceNote {
    private String title;
    private String content;
    private String password;

    public Note toNote() {
        return Note.builder()
                .title(this.title)
                .content(this.content)
                .password(password)
                .build();
    }

    public static PersistenceNote empty() {
        return new PersistenceNote();
    }
}
