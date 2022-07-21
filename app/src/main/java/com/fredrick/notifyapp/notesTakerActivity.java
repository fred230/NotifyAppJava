package com.fredrick.notifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fredrick.notifyapp.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class notesTakerActivity extends AppCompatActivity {

    EditText editText_title, editText_notes;
    ImageView imageview_save;
    Notes notes;
    Boolean isOldNotes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        imageview_save = findViewById(R.id.imageview_save);
        editText_notes = findViewById(R.id.editText_notes);
        editText_title = findViewById(R.id.editText_title);

        //editing of notes
        notes =new Notes();
        try{
        notes = (Notes) getIntent().getSerializableExtra("old_note");
        editText_title.setText(notes.getTitle());
        editText_notes.setText(notes.getNotes());
        isOldNotes = true;

        } catch (Exception e){
            e.printStackTrace();
        }



        imageview_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editText_title.getText().toString();
                String description  = editText_notes.getText().toString();

                if(description.isEmpty()){
                    Toast.makeText(notesTakerActivity.this, "Please add notes ", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat farmatter=  new SimpleDateFormat("EEE, d MMM yyy HH:mm a");
                Date date = new Date();

                if(!isOldNotes){
                    notes= new Notes();
                }


                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(farmatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note",notes);
                setResult(Activity.RESULT_OK, intent);
                finish();


            }
        });
    }


}