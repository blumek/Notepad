package com.blumek.notepad.adapter.repository.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.blumek.notepad.domain.entity.Note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RoomNote {
    @PrimaryKey
    @NonNull
    private String id;
    private String title;
    private String content;
    private String password;

    public static RoomNote from(Note note) {
        return RoomNote.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .password(note.getPassword())
                .build();
    }

    public Note toNote() {
        return Note.builder()
                .id(id)
                .title(title)
                .content(content)
                .password(password)
                .build();
    }
}
