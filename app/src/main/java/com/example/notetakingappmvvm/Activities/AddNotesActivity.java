package com.example.notetakingappmvvm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.example.notetakingappmvvm.Model.Notes;
import com.example.notetakingappmvvm.R;
import com.example.notetakingappmvvm.ViewModel.NotesViewModel;
import com.example.notetakingappmvvm.databinding.ActivityAddNotesBinding;

import java.util.Date;

public class AddNotesActivity extends AppCompatActivity {

    ActivityAddNotesBinding binding;
    String title,subTitle, notes;
    String priority = "1";

    // View Model
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


        // When user click on done Action
        binding.insertNotesActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get data from Input Edit Text ================================
                title = binding.addNotesTitleEdt.getText().toString();
                subTitle = binding.addNotesSubTitleEdt.getText().toString();
                notes = binding.addNotesEdt.getText().toString();


                // Create Notes method
                createNotes(title,subTitle,notes);
            }
        });



        // click Listeners on Priority buttons
        binding.greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Add Done Icon =============
                binding.greenPriority.setImageResource(R.drawable.done_icon);
                // Remove Done image or icon from both yellow and red
                binding.redPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(0);

                // set Priority
                priority = "1";
            }
        });

        binding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add Done Icon =============
                binding.greenPriority.setImageResource(0);
                // Remove Done image or icon from both yellow and red
                binding.redPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(R.drawable.done_icon);

                // set Priority
                priority = "2";
            }
        });

        binding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add Done Icon =============
                binding.greenPriority.setImageResource(0);
                // Remove Done image or icon from both yellow and red
                binding.redPriority.setImageResource(R.drawable.done_icon);
                binding.yellowPriority.setImageResource(0);

                // set Priority
                priority = "3";
            }
        });
    }

    private void createNotes(String title, String subTitle, String notes) {
        Notes notes1 = new Notes();

        // Add Date also
        Date date = new Date();

        CharSequence sequence = DateFormat.format("MMMM d, yyyy",date.getTime());

        // Add Notes
        notes1.notesTitle = title;
        notes1.notesSubtitle = subTitle;
        notes1.notes = notes;
        notes1.notesDate = sequence.toString();
        notes1.notesPriority = priority;

        notesViewModel.insertNotesVm(notes1);

        Toast.makeText(this, "Notes Created SuccessFully", Toast.LENGTH_SHORT).show();
        finish();
    }
}