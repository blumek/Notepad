package com.blumek.notepad.adapter.note_content_encoder;

import android.util.Base64;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentDecoder;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class AESNoteContentDecoder implements NoteContentDecoder {
    private static final String AES_MODE = "AES/GCM/NoPadding";

    private final Key key;
    private final byte[] initializationVector;

    @Override
    public Note decodeContent(Note note) {
        String decodedContent = decrypt(note.getContent());
        return note.toBuilder()
                .content(decodedContent)
                .build();
    }

    @SneakyThrows
    private String decrypt(String encryptedContent) {
        byte[] encryptedDecodedContent = Base64.decode(encryptedContent, Base64.DEFAULT);
        Cipher c = Cipher.getInstance(AES_MODE);
        GCMParameterSpec gcmParameter = new GCMParameterSpec(128, initializationVector);
        c.init(Cipher.DECRYPT_MODE, key, gcmParameter);
        byte[] decodedBytes = c.doFinal(encryptedDecodedContent);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
