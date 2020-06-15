package com.blumek.notepad.application.notes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.DiffResult;
import androidx.recyclerview.widget.RecyclerView;

import com.blumek.notepad.R;
import com.blumek.notepad.databinding.NoteListItemBinding;
import com.google.common.collect.Lists;

import java.util.List;

public final class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<ViewNoteShort> viewNotesShort;

    public NotesAdapter() {
        viewNotesShort = Lists.newArrayList();
    }

    public void setViewNotesShort(List<ViewNoteShort> viewNotesShort) {
        DiffResult diffResultOfViewNotes = getDiffResult(viewNotesShort);

        this.viewNotesShort = viewNotesShort;
        diffResultOfViewNotes.dispatchUpdatesTo(this);
    }

    private DiffResult getDiffResult(List<ViewNoteShort> viewNotesShort) {
        ViewNoteShortDiffCallback profileDiffCallback =
                new ViewNoteShortDiffCallback(this.viewNotesShort, viewNotesShort);
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
        holder.bind(viewNotesShort.get(position));
    }

    @Override
    public int getItemCount() {
        return viewNotesShort.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final NoteListItemBinding binding;

        ViewHolder(NoteListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ViewNoteShort viewNoteShort) {
            binding.setNote(viewNoteShort);
            binding.executePendingBindings();
        }
    }
}
