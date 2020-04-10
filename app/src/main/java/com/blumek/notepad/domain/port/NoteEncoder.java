package com.blumek.notepad.domain.port;

import com.blumek.notepad.domain.entity.Note;

public interface NoteEncoder {
    Note encode(Note note);
}
