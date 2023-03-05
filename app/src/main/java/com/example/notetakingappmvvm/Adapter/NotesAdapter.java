package com.example.notetakingappmvvm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notetakingappmvvm.Activities.UpdateNotesActivity;
import com.example.notetakingappmvvm.Model.Notes;
import com.example.notetakingappmvvm.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    // Or using context we can directly create a  the MainActivity mainActivity ; object to refer them as an activity context

    Context context;
    List<Notes> notesList;
    List<Notes> searchGetAllNotes;

    public NotesAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
        searchGetAllNotes = new ArrayList<>(notesList);
    }

    public void searchNotes(List<Notes> filterSearchNoteName)
    {
       this.notesList = filterSearchNoteName;
       notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_design_notes, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        // Get Notes
        Notes myNotesList = notesList.get(position);

        // Check notes priority
        if(myNotesList.notesPriority.equals("1"))
        {
            holder.notesPriority.setBackgroundResource(R.drawable.green_dot);
        }
        else if(myNotesList.notesPriority.equals("2"))
        {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_dot);
        }
        else if(myNotesList.notesPriority.equals("3"))
        {
            holder.notesPriority.setBackgroundResource(R.drawable.red_dot);
        }

        holder.title.setText(myNotesList.notesTitle);
        holder.subTitle.setText(myNotesList.notesSubtitle);
        holder.notesDate.setText(myNotesList.notesDate);

        // Update items ===================================== We need to set onclick to our notes Item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We send position as well as data to Update Activity class
                // We send data using Intent
                Intent intent = new Intent(context, UpdateNotesActivity.class);
                intent.putExtra("id",myNotesList.id);
                intent.putExtra("title",myNotesList.notesTitle);
                intent.putExtra("subTitle",myNotesList.notesSubtitle);
                intent.putExtra("notes",myNotesList.notes);
                intent.putExtra("priority",myNotesList.notesPriority);

                context.startActivity(intent);
            }
        });

    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView title, subTitle, notesDate;
        View notesPriority;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemNotesTitle);
            subTitle = itemView.findViewById(R.id.itemNotesSubTitle);
            notesDate = itemView.findViewById(R.id.itemNotesDate);
            notesPriority = itemView.findViewById(R.id.itemNotesPriority);

        }
    }
}
