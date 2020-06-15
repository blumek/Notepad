package com.blumek.notepad.application.notes;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

final class ViewNoteShortDiffCallback extends DiffUtil.Callback {
    private final List<ViewNoteShort> oldViewNotesShort;
    private final List<ViewNoteShort> newViewNotesShort;

    public ViewNoteShortDiffCallback(List<ViewNoteShort> oldViewNotesShort, List<ViewNoteShort> newViewNotesShort) {
        this.oldViewNotesShort = oldViewNotesShort;
        this.newViewNotesShort = newViewNotesShort;
    }

    @Override
    public int getOldListSize() {
        return oldViewNotesShort.size();
    }

    @Override
    public int getNewListSize() {
        return newViewNotesShort.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ViewNoteShort oldNote = oldViewNotesShort.get(oldItemPosition);
        ViewNoteShort newNote = newViewNotesShort.get(newItemPosition);
        return oldNote.equals(newNote);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areItemsTheSame(oldItemPosition, newItemPosition);
    }
}
