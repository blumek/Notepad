package com.blumek.notepad.application.adapter.diff_util;

import androidx.recyclerview.widget.DiffUtil;

import com.blumek.notepad.application.model.ViewNote;

import java.util.List;

public class ViewNoteDiffCallback extends DiffUtil.Callback {
    private final List<ViewNote> oldViewNotes;
    private final List<ViewNote> newViewNotes;

    public ViewNoteDiffCallback(List<ViewNote> oldViewNotes, List<ViewNote> newViewNotes) {
        this.oldViewNotes = oldViewNotes;
        this.newViewNotes = newViewNotes;
    }

    @Override
    public int getOldListSize() {
        return oldViewNotes.size();
    }

    @Override
    public int getNewListSize() {
        return newViewNotes.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ViewNote oldNote = oldViewNotes.get(oldItemPosition);
        ViewNote newNote = newViewNotes.get(newItemPosition);
        return oldNote.equals(newNote);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areItemsTheSame(oldItemPosition, newItemPosition);
    }
}
