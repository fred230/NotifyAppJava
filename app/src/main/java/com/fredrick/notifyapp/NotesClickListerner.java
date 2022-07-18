package com.fredrick.notifyapp;

import androidx.cardview.widget.CardView;

import com.fredrick.notifyapp.Models.Notes;

public interface NotesClickListerner {

    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}
