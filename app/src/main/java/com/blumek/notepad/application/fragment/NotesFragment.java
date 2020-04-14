package com.blumek.notepad.application.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.R;
import com.blumek.notepad.adapter.repository.RoomNoteRepository;
import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.application.adapter.NotesAdapter;
import com.blumek.notepad.application.database.AppDatabase;
import com.blumek.notepad.application.view_model.NotesViewModel;
import com.blumek.notepad.application.view_model.factory.NotesViewModelFactory;
import com.blumek.notepad.databinding.NotesFragmentBinding;
import com.blumek.notepad.usecase.FindNote;

public class NotesFragment extends Fragment {
    private NotesViewModel viewModel;
    private NotesAdapter notesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesAdapter = new NotesAdapter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        NotesFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.notes_fragment,
                container, false);

        binding.setAdapter(notesAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NoteDao noteDao = AppDatabase.getInstance(getActivity()).noteDao();
        RoomNoteRepository noteRepository = new RoomNoteRepository(noteDao);
        FindNote findNote = new FindNote(noteRepository);
        NotesViewModelFactory notesViewModelFactory = new NotesViewModelFactory(findNote);
        viewModel = new ViewModelProvider(this, notesViewModelFactory)
                .get(NotesViewModel.class);

        observeNotes();
    }

    private void observeNotes() {
        viewModel.getViewNotes().observe(getViewLifecycleOwner(), notesAdapter::setViewNotes);
    }

}
