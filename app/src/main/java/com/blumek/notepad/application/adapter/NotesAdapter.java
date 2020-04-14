package com.blumek.notepad.application.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.notepad.R;
import com.blumek.notepad.application.adapter.diff_util.ViewNoteDiffCallback;
import com.blumek.notepad.application.model.ViewNote;
import com.blumek.notepad.databinding.NoteListItemBinding;
import com.google.common.collect.Lists;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<ViewNote> viewNotes;

    public NotesAdapter() {
        viewNotes = Lists.newArrayList();
    }

    public NotesAdapter(List<ViewNote> viewNotes) {
        this.viewNotes = viewNotes;
    }

    public void setViewNotes(List<ViewNote> viewNotes) {
        DiffUtil.DiffResult diffResultOfViewNotes = getDiffResult(viewNotes);

        this.viewNotes = viewNotes;
        diffResultOfViewNotes.dispatchUpdatesTo(this);
    }

    private DiffUtil.DiffResult getDiffResult(List<ViewNote> viewNotes) {
        ViewNoteDiffCallback profileDiffCallback =
                new ViewNoteDiffCallback(this.viewNotes, viewNotes);
        return DiffUtil.calculateDiff(profileDiffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.note_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(viewNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return viewNotes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final NoteListItemBinding binding;

        ViewHolder(NoteListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ViewNote viewNote) {
            binding.setNote(viewNote);
            binding.executePendingBindings();
        }
    }
}
