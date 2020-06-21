package com.blumek.notepad.adapter.note_content_decoder;

import android.util.Base64;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentEncoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
public class AESNoteContentEncoder implements NoteContentEncoder {
    private static final String AES_MODE = "AES/GCM/NoPadding";

    private final Key key;
    private final byte[] initializationVector;

    @Override
    public Note encodeContent(Note note) {
        String encodedContent = encrypt(note.getContent());
        return note.toBuilder()
                .content(encodedContent)
                .build();
    }

    @SneakyThrows
    private String encrypt(String content) {
        Cipher c = Cipher.getInstance(AES_MODE);
        GCMParameterSpec gcmParameter = new GCMParameterSpec(128, initializationVector);
        c.init(Cipher.ENCRYPT_MODE, key, gcmParameter);
        byte[] encodedBytes = c.doFinal(content.getBytes(UTF_8));
        return Base64.encodeToString(encodedBytes, Base64.DEFAULT);
    }
}
