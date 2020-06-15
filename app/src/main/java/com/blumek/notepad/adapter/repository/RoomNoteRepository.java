package com.blumek.notepad.adapter.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.adapter.repository.model.RoomNote;
import com.blumek.notepad.domain.entity.Note;
import com.blumek.notepad.domain.port.NoteRepository;


import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.toList;

public class RoomNoteRepository implements NoteRepository {
    private final NoteDao noteDao;

    public RoomNoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public LiveData<Optional<Note>> findById(String id) {
        return Transformations.map(noteDao.findById(id), this::mapToOptionalNote);
    }

    private Optional<Note> mapToOptionalNote(RoomNote roomNote) {
        if (roomNote == null)
            return Optional.empty();

        return Optional.of(roomNote.toNote());
    }

    @Override
    public LiveData<List<Note>> findAll() {
        return Transformations.map(noteDao.findAll(), this::mapToNoteList);
    }

    private List<Note> mapToNoteList(List<RoomNote> roomNotes) {
        return roomNotes
                .stream()
                .map(RoomNote::toNote)
                .collect(toList());
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

    @RequiredArgsConstructor
    private static class CreateNoteAsyncTask extends AsyncTask<RoomNote, Void, Void> {
        private final NoteDao noteDao;

        @Override
        protected Void doInBackground(RoomNote... roomNotes) {
            noteDao.save(roomNotes[0]);
            return null;
        }
    }

    @RequiredArgsConstructor
    private static class UpdateNoteAsyncTask extends AsyncTask<RoomNote, Void, Void> {
        private final NoteDao noteDao;

        @Override
        protected Void doInBackground(RoomNote... roomNotes) {
            noteDao.update(roomNotes[0]);
            return null;
        }
    }

    @RequiredArgsConstructor
    private static class DeleteNoteAsyncTask extends AsyncTask<RoomNote, Void, Void> {
        private final NoteDao noteDao;

        @Override
        protected Void doInBackground(RoomNote... roomNotes) {
            noteDao.delete(roomNotes[0]);
            return null;
        }
    }
}
