package com.blumek.notepad.application;

import android.app.Application;

import static com.blumek.notepad.application.crypto.AES.KEY;


public class ApplicationBase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationKeyGenerator applicationKeyGenerator = new ApplicationKeyGenerator();
        applicationKeyGenerator.generateAESKey(KEY);
    }
}
