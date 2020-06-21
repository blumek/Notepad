package com.blumek.notepad.application.note_persistence;

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
import com.blumek.notepad.adapter.note_content_decoder.AESNoteContentEncoder;
import com.blumek.notepad.adapter.note_validator.NoteCreationValidator;
import com.blumek.notepad.adapter.password_hasher.SHA256PasswordHasher;
import com.blumek.notepad.adapter.repository.EncodedNoteContentRepository;
import com.blumek.notepad.adapter.repository.GeneratedIdNoteRepository;
import com.blumek.notepad.adapter.repository.HashedNotePasswordRepository;
import com.blumek.notepad.adapter.repository.RoomNoteRepository;
import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.application.AppDatabase;
import com.blumek.notepad.application.ApplicationKeyStore;
import com.blumek.notepad.databinding.NoteCreationFragmentBinding;
import com.blumek.notepad.domain.port.NoteContentEncoder;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.usecase.CreateNote;

import static com.blumek.notepad.application.crypto.AES.INITIALIZATION_VECTOR;
import static com.blumek.notepad.application.crypto.AES.KEY;

public final class NoteCreationFragment extends Fragment {
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
        NoteCreationViewModel viewModel = getViewModel();

        binding.setViewModel(viewModel);
    }

    private NoteCreationViewModel getViewModel() {
        AppDatabase database = AppDatabase.getInstance(getContext());
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

        CreateNote createNote = new CreateNote(noteRepository, new NoteCreationValidator());
        NoteCreationViewModelFactory noteCreationViewModelFactory =
                new NoteCreationViewModelFactory(createNote);

        return new ViewModelProvider(this, noteCreationViewModelFactory)
                .get(NoteCreationViewModel.class);
    }

}