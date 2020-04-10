package com.blumek.notepad.adapter.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;


import java.util.List;

public class RoomNoteRepository implements NoteRepository {
    private final NoteDao noteDao;

    public RoomNoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public LiveData<Note> findById(String id) {
        return noteDao.findById(id);
    }

    @Override
    public LiveData<List<Note>> findAll() {
        return noteDao.findAll();
    }

    @Override
    public void create(Note note) {
        new CreateNoteAsyncTask(noteDao)
                .execute(note);
    }

    @Override
    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao)
                .execute(note);
    }

    @Override
    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao)
                .execute(note);
    }

    private static class CreateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private CreateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.save(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
}
