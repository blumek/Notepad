package com.blumek.notepad.application.change_note_password;

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
import com.blumek.notepad.adapter.note_validator.NoteUpdateValidator;
import com.blumek.notepad.adapter.password_hasher.SHA256PasswordHasher;
import com.blumek.notepad.adapter.repository.HashedNotePasswordRepository;
import com.blumek.notepad.adapter.repository.RoomNoteRepository;
import com.blumek.notepad.adapter.repository.dao.NoteDao;
import com.blumek.notepad.application.AppDatabase;
import com.blumek.notepad.application.note_details.NoteDetailsFragmentArgs;
import com.blumek.notepad.databinding.ChangeNotePasswordFragmentBinding;
import com.blumek.notepad.domain.port.NoteRepository;
import com.blumek.notepad.domain.port.PasswordHasher;
import com.blumek.notepad.usecase.ChangeNotePassword;
import com.blumek.notepad.usecase.FindNote;
import com.blumek.notepad.usecase.UpdateNote;

public final class ChangeNotePasswordFragment extends Fragment {
    private ChangeNotePasswordFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.change_note_password_fragment, container, false);

        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String noteId = NoteDetailsFragmentArgs.fromBundle(getInitialArguments()).getId();
        ChangeNotePasswordViewModel viewModel = getViewModel(noteId);

        binding.setViewModel(viewModel);
    }

    private Bundle getInitialArguments() {
        Bundle arguments = getArguments();
        if (arguments == null)
            throw new NullPointerException();

        return arguments;
    }

    private ChangeNotePasswordViewModel getViewModel(String noteId) {
        AppDatabase database = AppDatabase.getInstance(getContext());
        NoteDao noteDao = database.noteDao();

        NoteRepository noteRepository = new RoomNoteRepository(noteDao);

        FindNote findNote = new FindNote(noteRepository);

        PasswordHasher passwordHasher = new SHA256PasswordHasher();

        NoteRepository updateNoteRepository =
                new HashedNotePasswordRepository(noteRepository, passwordHasher);

        UpdateNote updateNote = new UpdateNote(updateNoteRepository, new NoteUpdateValidator());

        ChangeNotePassword changeNotePassword = new ChangeNotePassword(updateNote, findNote);

        ChangeNotePasswordViewModelFactory changeNotePasswordViewModelFactory =
                new ChangeNotePasswordViewModelFactory(changeNotePassword, findNote, noteId,
                        passwordHasher);

        return new ViewModelProvider(this, changeNotePasswordViewModelFactory)
                .get(ChangeNotePasswordViewModel.class);
    }

}