package com.fredrick.notifyapp.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fredrick.notifyapp.Models.Notes;
import com.fredrick.notifyapp.NotesClickListerner;
import com.fredrick.notifyapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends  RecyclerView.Adapter<NotesViewHolder> {

    Context context;
    List<Notes> list;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListerner listerner) {
        this.context = context;
        this.list = list;
        this.listerner = listerner;
    }

    NotesClickListerner listerner;


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textview_title.setText(list.get(position).getTitle());
        holder.textview_title.setSelected(true);

        holder.textview_notes.setText(list.get(position).getNotes());

        holder.textview_date.setText(list.get(position).getDate());
        holder.textview_date.setSelected(true);

        if(list.get(position).isPinned()){
            holder.imageview_pin.setImageResource(R.drawable.ic_pin);
        }else {
            holder.imageview_pin.setImageResource(0);
        }



        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code,null));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listerner.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listerner.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);
                return true;
            }
        });

    }
    private int getRandomColor(){
        List<Integer> colorCodes  = new ArrayList<>();
        colorCodes.add(R.color.c1);
        colorCodes.add(R.color.c2);
        colorCodes.add(R.color.c3);
        colorCodes.add(R.color.c4);
        colorCodes.add(R.color.c5);

        Random random = new Random();

        int random_color =  random.nextInt(colorCodes.size());
        return colorCodes.get(random_color);


    }

    @Override
    public int getItemCount() {
        return list.size();



    }


    public void filterList(List<Notes>  filteredList){

        list = filteredList;
        notifyDataSetChanged();

    }
}


class NotesViewHolder extends RecyclerView.ViewHolder {
CardView notes_container;
TextView textview_title, textview_notes,textview_date;
ImageView imageview_pin;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);


        notes_container  = itemView.findViewById(R.id.notes_container);
        textview_title = itemView.findViewById(R.id.textview_title);
        textview_notes = itemView.findViewById(R.id.textview_notes);
        textview_date = itemView.findViewById(R.id.textview_date);
        imageview_pin= itemView.findViewById(R.id.imageview_pin);

    }
}