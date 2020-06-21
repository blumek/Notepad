package com.blumek.notepad.application.crypto;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AES {
    public static final String KEY = "AES";
    public static final byte[] INITIALIZATION_VECTOR =
            new byte[]{ 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44 };
}
