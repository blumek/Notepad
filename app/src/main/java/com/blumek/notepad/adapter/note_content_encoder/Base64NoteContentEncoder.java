package com.blumek.notepad.adapter.note_content_encoder;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentEncoder;

import org.apache.commons.codec.binary.Base64;


public class Base64NoteContentEncoder implements NoteContentEncoder {
    @Override
    public Note encodeContent(Note note) {
        String content = note.getContent();
        if (content == null)
            return note;

        String encodedContent = Base64.encodeBase64String(content.getBytes());

        return note.toBuilder()
                .content(encodedContent)
                .build();
    }
}
