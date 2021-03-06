package com.blumek.notepad.adapter.note_content_decoder;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentDecoder;

import org.apache.commons.codec.binary.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;


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
        byte[] encodedContentBytes = encodedContent.getBytes(UTF_8);
        return new String(Base64.decodeBase64(encodedContentBytes), UTF_8);
    }
}
