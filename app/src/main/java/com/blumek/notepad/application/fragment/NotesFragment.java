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
import com.blumek.notepad.application.NotepadApplication;
import com.blumek.notepad.application.adapter.NotesAdapter;
import com.blumek.notepad.application.view_model.NotesViewModel;
import com.blumek.notepad.application.view_model.factory.ViewModelFactory;
import com.blumek.notepad.databinding.NotesFragmentBinding;

import javax.inject.Inject;

public class NotesFragment extends Fragment {
    @Inject
    ViewModelFactory viewModelFactory;
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
        inject();
        viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(NotesViewModel.class);

        observeNotes();
    }

    @SuppressWarnings("ConstantConditions")
    private void inject() {
        ((NotepadApplication) getActivity().getApplication()).getComponent()
                .inject(this);
    }

    private void observeNotes() {
        viewModel.getViewNotes().observe(getViewLifecycleOwner(), notesAdapter::setViewNotes);
    }

}
