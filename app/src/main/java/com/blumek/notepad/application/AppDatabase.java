package com.blumek.notepad.application;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.blumek.notepad.adapter.id_generator.UUIDGenerator;
import com.blumek.notepad.adapter.note_content_decoder.AESNoteContentEncoder;
import com.blumek.notepad.adapter.note_validator.NoteCreationValidator;
import com.blumek.notepad.adapter.password_hasher.SHA256PasswordHasher;
import com.blumek.notepad.adapter.repository.EncodedNoteContentRepository;
import com.blumek.notepad.adapter.repository.GeneratedIdNoteRepository;
import com.blumek.notepad.adapter.repository.HashedNotePasswordRepository;
import com.blumek.notepad.adapter.repository.RoomNoteRepository;
import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.adapter.repository.model.RoomNote;
import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteContentEncoder;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.usecase.CreateNote;

import static com.blumek.notepad.application.crypto.AES.INITIALIZATION_VECTOR;
import static com.blumek.notepad.application.crypto.AES.KEY;

@Database(entities = RoomNote.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "app_database";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract NoteDao noteDao();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final CreateNote createNote;

        private PopulateDbAsyncTask(AppDatabase database) {
            NoteDao noteDao = database.noteDao();
            ApplicationKeyStore applicationKeyStore = new ApplicationKeyStore();
            NoteContentEncoder noteContentEncoder = new AESNoteContentEncoder(
                    applicationKeyStore.getKey(KEY), INITIALIZATION_VECTOR);
            NoteRepository noteRepository = new GeneratedIdNoteRepository(
                    new EncodedNoteContentRepository
                            (new HashedNotePasswordRepository(
                                    new RoomNoteRepository(noteDao), new SHA256PasswordHasher()
                            ), noteContentEncoder),
                    new UUIDGenerator()
            );

            this.createNote = new CreateNote(noteRepository, new NoteCreationValidator());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            persistSampleData();
            return null;
        }

        private void persistSampleData() {
            Note firstNote = Note.builder()
                    .title("First Note")
                    .content("Sample content")
                    .password("HASLO123")
                    .build();

            Note secondNote = Note.builder()
                    .title("Second Note")
                    .content("Sample content...")
                    .build();

            Note thirdNote = Note.builder()
                    .title("Third Note")
                    .content("...Sample content...")
                    .password("PASSWORD123")
                    .build();

            createNote.create(firstNote);
            createNote.create(secondNote);
            createNote.create(thirdNote);
        }
    }
}