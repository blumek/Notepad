package com.blumek.notepad.application.note_creation;

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
import com.blumek.notepad.adapter.id_generator.UUIDGenerator;
import com.blumek.notepad.adapter.note_content_encoder.Base64NoteContentEncoder;
import com.blumek.notepad.adapter.note_validator.NoteCreationValidator;
import com.blumek.notepad.adapter.repository.EncodedNoteContentRepository;
import com.blumek.notepad.adapter.repository.GeneratedIdNoteRepository;
import com.blumek.notepad.adapter.repository.RoomNoteRepository;
import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.application.AppDatabase;
import com.blumek.notepad.databinding.NoteCreationFragmentBinding;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.usecase.CreateNote;

public final class NoteCreationFragment extends Fragment {
    private NoteCreationViewModel viewModel;
    private NoteCreationFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.note_creation_fragment, container, false);

        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = getViewModel();

        binding.setViewModel(viewModel);
    }

    private NoteCreationViewModel getViewModel() {
        AppDatabase database = AppDatabase.getInstance(getContext());
        NoteDao noteDao = database.noteDao();
        NoteRepository noteRepository = new EncodedNoteContentRepository(
                new GeneratedIdNoteRepository(
                        new RoomNoteRepository(noteDao),
                        new UUIDGenerator()),
                new Base64NoteContentEncoder()
        );

        CreateNote createNote = new CreateNote(noteRepository, new NoteCreationValidator());
        NoteCreationViewModelFactory noteCreationViewModelFactory =
                new NoteCreationViewModelFactory(createNote);

        return new ViewModelProvider(this, noteCreationViewModelFactory)
                .get(NoteCreationViewModel.class);
    }

}