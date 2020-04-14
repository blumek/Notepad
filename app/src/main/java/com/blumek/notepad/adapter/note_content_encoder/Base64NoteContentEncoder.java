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

        String encodedContent = getEncodedContent(content);

        return note.toBuilder()
                .content(encodedContent)
                .build();
    }

    private String getEncodedContent(String content) {
        return new String(Base64.encodeBase64(content.getBytes()));
    }
}
