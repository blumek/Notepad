package com.blumek.notepad.adapter.note_content_decoder;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentDecoder;

import org.apache.commons.codec.binary.Base64;

public class Base64NoteContentDecoder implements NoteContentDecoder {
    @Override
    public Note decodeContent(Note note) {
        String encodedContent = note.getContent();
        if (encodedContent == null)
            return note;

        String decodedContent = getDecodedContent(encodedContent);
        return note.toBuilder()
                .content(decodedContent)
                .build();
    }

    private String getDecodedContent(String encodedContent) {
        return new String(Base64.decodeBase64(encodedContent));
    }
}
