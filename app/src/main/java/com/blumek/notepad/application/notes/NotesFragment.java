package com.blumek.notepad.application.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.R;
import com.blumek.notepad.adapter.password_hasher.SHA256PasswordHasher;
import com.blumek.notepad.adapter.repository.RoomNoteRepository;
import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.application.AppDatabase;
import com.blumek.notepad.application.notes.NotesAdapter.NoteActionListener;
import com.blumek.notepad.databinding.NotesFragmentBinding;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.usecase.FindNote;
import com.blumek.notepad.usecase.NoteAuthentication;

import java.util.Objects;

public final class NotesFragment extends Fragment {
    private NotesViewModel viewModel;
    private NotesAdapter notesAdapter;
    private NotesFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.notes_fragment,
                container, false);

        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NoteActionListener listener = noteShort -> {
            if (!noteShort.hasPassword())
                viewModel.openNoteDetails(view, noteShort.getId());
            else
                openPasswordDialog(view, noteShort.getId());
        };

        notesAdapter = new NotesAdapter(listener);
        binding.setAdapter(notesAdapter);
    }

    private void openPasswordDialog(View view, String id) {
        DialogFragment dialog = new NotePasswordDialog(password ->
                authenticationListener(view, id, password));

        dialog.show(getSupportFragmentManager(), null);
    }

    private void authenticationListener(View view, String id, String password) {
        viewModel.authenticateWithPassword(id, password)
                .observe(getViewLifecycleOwner(), isAuthenticated -> {
                    if (isAuthenticated)
                        viewModel.openNoteDetails(view, id);
                });
    }

    private FragmentManager getSupportFragmentManager() {
        return Objects.requireNonNull(getActivity())
                .getSupportFragmentManager();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = getViewModel();
        binding.setViewModel(viewModel);

        observeNotes();
    }

    private NotesViewModel getViewModel() {
        AppDatabase database = AppDatabase.getInstance(getContext());
        NoteDao noteDao = database.noteDao();
        NoteRepository noteRepository = new RoomNoteRepository(noteDao);
        FindNote findNote = new FindNote(noteRepository);
        NoteAuthentication noteAuthentication = new NoteAuthentication(noteRepository,
                new SHA256PasswordHasher());
        NotesViewModelFactory notesViewModelFactory = new NotesViewModelFactory(findNote, noteAuthentication);

        return new ViewModelProvider(this, notesViewModelFactory)
                .get(NotesViewModel.class);
    }

    private void observeNotes() {
        viewModel.getViewNotes()
                .observe(getViewLifecycleOwner(), notesAdapter::setViewNotesShort);
    }

}
