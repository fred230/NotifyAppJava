package com.fredrick.notifyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fredrick.notifyapp.Adapters.NotesListAdapter;
import com.fredrick.notifyapp.DataBase.RoomDB;
import com.fredrick.notifyapp.Models.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    List<Notes> notes = new ArrayList<>();
    RoomDB database;
    FloatingActionButton fab_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);
        database = RoomDB.getInstance(this);
        notes = database.mainDAO().getAll();

        updateRecycler(notes);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, notesTakerActivity.class);
                startActivityForResult(intent,101);

            }
        });
    }

    private void updateRecycler(List<Notes> notes) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter= new NotesListAdapter(MainActivity.this,notes,notesClickListerner);

        recyclerView.setAdapter(notesListAdapter);
    }

    private final NotesClickListerner notesClickListerner = new NotesClickListerner() {
        @Override
        public void onClick(Notes notes) {

        }

        @Override
        public void onLongClick(Notes notes, CardView cardView) {

        }
    };


}