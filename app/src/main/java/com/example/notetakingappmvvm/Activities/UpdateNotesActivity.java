package com.example.notetakingappmvvm.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notetakingappmvvm.Model.Notes;
import com.example.notetakingappmvvm.R;
import com.example.notetakingappmvvm.ViewModel.NotesViewModel;
import com.example.notetakingappmvvm.databinding.ActivityAddNotesBinding;
import com.example.notetakingappmvvm.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;

    int upId;
    String upTitle,upSubTitle, upNotes, upPriority;
    String priority = "1";

    // View Model
    NotesViewModel updateNotesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updateNotesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);



        upId = getIntent().getIntExtra("id",0);
        upTitle = getIntent().getStringExtra("title");
        upSubTitle = getIntent().getStringExtra("subTitle");
        upPriority = getIntent().getStringExtra("priority");
        upNotes = getIntent().getStringExtra("notes");


        binding.updateNotesTitleEdt.setText(upTitle);
        binding.updateNotesSubTitleEdt.setText(upSubTitle);
        binding.updateNotesEdt.setText(upNotes);



        if (upPriority.equals("1"))
        {
            binding.greenPriority.setImageResource(R.drawable.done_icon);
        }
        else if(upPriority.equals("2"))
        {
            binding.greenPriority.setImageResource(R.drawable.done_icon);
        }
        else if(upPriority.equals("3"))
        {
            binding.greenPriority.setImageResource(R.drawable.done_icon);
        }


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



        // Update notes Action button =========================
        binding.updateNotesActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get data from Input Edit Text ================================
                upTitle = binding.updateNotesTitleEdt.getText().toString();
                upSubTitle = binding.updateNotesSubTitleEdt.getText().toString();
                upNotes = binding.updateNotesEdt.getText().toString();


                // Create Notes method
                updateNotes(upTitle,upSubTitle,upNotes);

            }
        });
    }

    private void updateNotes(String upTitle, String upSubTitle, String upNotes) {

        Notes updateNotes = new Notes();

        // Add Date also
        Date date = new Date();

        CharSequence sequence = DateFormat.format("MMMM d, yyyy",date.getTime());

        // Update Notes
        updateNotes.id = upId;
        updateNotes.notesTitle = upTitle;
        updateNotes.notesSubtitle = upSubTitle;
        updateNotes.notes = upNotes;
        updateNotes.notesDate = sequence.toString();
        updateNotes.notesPriority = priority;

        updateNotesViewModel.updateNotesVm(updateNotes);

        Toast.makeText(this, "Notes Updated SuccessFully", Toast.LENGTH_SHORT).show();
        finish();

    }


    // Delete Menu in Toolbar ===============================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu_update,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.deleteMenuBtn)
        {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNotesActivity.this,R.style.bottomSheetStyle);

            View view = LayoutInflater.from(UpdateNotesActivity.this)
                    .inflate(R.layout.delete_item_bottom_sheet,
                            (LinearLayout)findViewById(R.id.deleteBottomSheet));
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();

            // Get Yes or No TextView
            TextView deleteNo,deleteYes;

            deleteNo = view.findViewById(R.id.noDeleteBtnSheet);
            deleteYes = view.findViewById(R.id.yesDeleteBtnSheet);

            deleteNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            deleteYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateNotesViewModel.deleteNotesVm(upId);

                    Toast.makeText(UpdateNotesActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    // finish the activity
                    finish();
                }
            });

        }
        return super.onOptionsItemSelected(item);
    }
}