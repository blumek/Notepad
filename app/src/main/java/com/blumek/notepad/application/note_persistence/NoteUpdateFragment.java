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
import com.blumek.notepad.adapter.note_content_encoder.AESNoteContentDecoder;
import com.blumek.notepad.adapter.note_validator.NoteUpdateValidator;
import com.blumek.notepad.adapter.repository.DecodedNoteContentRepository;
import com.blumek.notepad.adapter.repository.EncodedNoteContentRepository;
import com.blumek.notepad.adapter.repository.GeneratedIdNoteRepository;
import com.blumek.notepad.adapter.repository.RoomNoteRepository;
import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.application.AppDatabase;
import com.blumek.notepad.application.ApplicationKeyStore;
import com.blumek.notepad.databinding.NoteUpdateFragmentBinding;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.usecase.FindNote;
import com.blumek.notepad.usecase.UpdateNote;

import static com.blumek.notepad.application.crypto.AES.INITIALIZATION_VECTOR;
import static com.blumek.notepad.application.crypto.AES.KEY;

public final class NoteUpdateFragment extends Fragment {
    private NoteUpdateFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.note_update_fragment, container, false);

        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String noteId = NoteUpdateFragmentArgs.fromBundle(getInitialArguments()).getId();
        NoteUpdateViewModel viewModel = getViewModel(noteId);

        binding.setViewModel(viewModel);
    }

    private NoteUpdateViewModel getViewModel(String noteId) {
        AppDatabase database = AppDatabase.getInstance(getContext());
        NoteDao noteDao = database.noteDao();
        RoomNoteRepository noteRepository = new RoomNoteRepository(noteDao);

        ApplicationKeyStore applicationKeyStore = new ApplicationKeyStore();
        AESNoteContentEncoder noteContentEncoder = new AESNoteContentEncoder(
                applicationKeyStore.getKey(KEY), INITIALIZATION_VECTOR);
        NoteRepository noteUpdateRepository = new GeneratedIdNoteRepository(
                new EncodedNoteContentRepository(
                        noteRepository,
                        noteContentEncoder),
                new UUIDGenerator());

        UpdateNote updateNote = new UpdateNote(noteUpdateRepository, new NoteUpdateValidator());

        AESNoteContentDecoder noteContentDecoder = new AESNoteContentDecoder(
                applicationKeyStore.getKey(KEY), INITIALIZATION_VECTOR);
        NoteRepository noteFindRepository = new DecodedNoteContentRepository(
                noteRepository, noteContentDecoder);

        FindNote findNote = new FindNote(noteFindRepository);

        NoteUpdateViewModelFactory noteCreationViewModelFactory =
                new NoteUpdateViewModelFactory(updateNote, findNote, noteId);

        return new ViewModelProvider(this, noteCreationViewModelFactory)
                .get(NoteUpdateViewModel.class);
    }

    private Bundle getInitialArguments() {
        Bundle arguments = getArguments();
        if (arguments == null)
            throw new NullPointerException();

        return arguments;
    }

}