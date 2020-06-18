package com.blumek.notepad.application.note_persistence;

import com.blumek.notepad.domain.entity.Note;

import lombok.Data;

@Data
public final class PersistenceNote {
    private String id;
    private String title;
    private String content;
    private String password;

    public Note toNote() {
        return Note.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .password(password)
                .build();
    }

    public static PersistenceNote from(Note note) {
        PersistenceNote persistenceNote = new PersistenceNote();
        persistenceNote.setId(note.getId());
        persistenceNote.setTitle(note.getTitle());
        persistenceNote.setContent(note.getContent());
        persistenceNote.setPassword(note.getPassword());
        return persistenceNote;
    }

    public static PersistenceNote empty() {
        return new PersistenceNote();
    }
}
