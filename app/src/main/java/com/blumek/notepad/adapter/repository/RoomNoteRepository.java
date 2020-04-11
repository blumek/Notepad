package com.blumek.notepad.adapter.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.adapter.repository.model.RoomNote;
import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;


import java.util.List;

public class RoomNoteRepository implements NoteRepository {
    private final NoteDao noteDao;

    public RoomNoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public LiveData<Optional<Note>> findById(String id) {
        return Transformations.map(noteDao.findById(id), this::toOptionalNote);
    }

    private Optional<Note> toOptionalNote(RoomNote roomNote) {
        if (roomNote == null)
            return Optional.absent();

        return Optional.of(roomNote.toNote());
    }

    @Override
    public LiveData<List<Note>> findAll() {
        return Transformations.map(noteDao.findAll(), this::toNotes);
    }

    private List<Note> toNotes(List<RoomNote> roomNotes) {
        List<Note> notes = Lists.newArrayList();
        for (RoomNote roomNote : roomNotes)
            notes.add(roomNote.toNote());
        return notes;
    }

    @Override
    public void create(Note note) {
        new CreateNoteAsyncTask(noteDao)
                .execute(RoomNote.from(note));
    }

    @Override
    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao)
                .execute(RoomNote.from(note));
    }

    @Override
    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao)
                .execute(RoomNote.from(note));
    }

    private static class CreateNoteAsyncTask extends AsyncTask<RoomNote, Void, Void> {
        private NoteDao noteDao;

        private CreateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(RoomNote... roomNotes) {
            noteDao.save(roomNotes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<RoomNote, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(RoomNote... roomNotes) {
            noteDao.update(roomNotes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<RoomNote, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(RoomNote... roomNotes) {
            noteDao.delete(roomNotes[0]);
            return null;
        }
    }
}
