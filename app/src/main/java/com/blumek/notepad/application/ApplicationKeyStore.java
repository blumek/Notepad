package com.blumek.notepad.application;

import java.security.Key;
import java.security.KeyStore;

import lombok.SneakyThrows;

public class ApplicationKeyStore {
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    private final KeyStore keyStore;

    @SneakyThrows
    public ApplicationKeyStore() {
        keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
    }

    @SneakyThrows
    public Key getKey(String alias) {
        keyStore.load(null);
        return  keyStore.getKey(alias, null);
    }
}
