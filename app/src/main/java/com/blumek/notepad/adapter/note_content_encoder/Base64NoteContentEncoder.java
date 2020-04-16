package com.blumek.notepad.adapter.note_content_encoder;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentEncoder;

import org.apache.commons.codec.binary.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;


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
        byte[] contentBytes = content.getBytes(UTF_8);
        return new String(Base64.encodeBase64(contentBytes), UTF_8);
    }
}
