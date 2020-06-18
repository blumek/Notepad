package com.blumek.notepad.application.note_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.notepad.R;
import com.blumek.notepad.adapter.note_content_decoder.Base64NoteContentDecoder;
import com.blumek.notepad.adapter.repository.DecodedNoteContentRepository;
import com.blumek.notepad.adapter.repository.RoomNoteRepository;
import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.application.AppDatabase;
import com.blumek.notepad.databinding.NoteDetailsFragmentBinding;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.usecase.FindNote;

public final class NoteDetailsFragment extends Fragment {
    private NoteDetailsViewModel viewModel;
    private NoteDetailsFragmentBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.note_details_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.change_password) {
            viewModel.getNote().observe(getViewLifecycleOwner(),
                    note -> viewModel.openChangeNotePassword(getView(), note.getId()));
        }

        return true;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.note_details_fragment, container, false);

        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String noteId = NoteDetailsFragmentArgs.fromBundle(getInitialArguments()).getId();
        viewModel = getViewModel(noteId);
        binding.setViewModel(viewModel);
    }

    private Bundle getInitialArguments() {
        Bundle arguments = getArguments();
        if (arguments == null)
            throw new NullPointerException();

        return arguments;
    }

    private NoteDetailsViewModel getViewModel(String noteId) {
        AppDatabase database = AppDatabase.getInstance(getContext());
        NoteDao noteDao = database.noteDao();
        NoteRepository noteRepository = new DecodedNoteContentRepository(
                new RoomNoteRepository(noteDao), new Base64NoteContentDecoder());
        FindNote findNote = new FindNote(noteRepository);
        NoteDetailsViewModelFactory noteDetailsViewModelFactory = new NoteDetailsViewModelFactory(findNote, noteId);

        return new ViewModelProvider(this, noteDetailsViewModelFactory)
                .get(NoteDetailsViewModel.class);
    }

}