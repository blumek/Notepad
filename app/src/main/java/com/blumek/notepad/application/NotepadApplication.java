package com.blumek.notepad.application;

import android.app.Application;

import com.blumek.notepad.application.config.ApplicationComponent;
import com.blumek.notepad.application.config.ApplicationModule;
import com.blumek.notepad.application.config.DaggerApplicationComponent;
import com.blumek.notepad.application.config.RoomModule;
import com.blumek.notepad.application.database.AppDatabase;

public class NotepadApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(AppDatabase.getInstance(this)))
                .build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
