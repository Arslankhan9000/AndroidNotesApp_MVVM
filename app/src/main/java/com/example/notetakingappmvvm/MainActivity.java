package com.example.notetakingappmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.notetakingappmvvm.Activities.AddNotesActivity;
import com.example.notetakingappmvvm.Adapter.NotesAdapter;
import com.example.notetakingappmvvm.Model.Notes;
import com.example.notetakingappmvvm.ViewModel.NotesViewModel;
import com.example.notetakingappmvvm.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    // Recycler View and Adapter
    NotesAdapter notesAdapter;

    // View Model
    NotesViewModel notesViewModel;

    // List
    List<Notes> filterNameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.addNotesActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNotesActivity.class));
            }
        });


        // Live data provide a observer through which we can observe a data 

//        notesViewModel.getAllNotesVm.observe(this,notesList -> {
//            // We get a list of notes were we can show in recycler
//
//            binding.notesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
//            notesAdapter = new NotesAdapter(this,notesList);
//            binding.notesRecyclerView.setAdapter(notesAdapter);
//
//
//        });






        // ================================================================================
        // filters ================================================
        binding.noFilter.setBackgroundResource(R.drawable.filter_selected_shape);

        // Click Listener apply to other items  for Selected shape

        binding.noFilter.setOnClickListener(v -> {
            filterData(0);
            binding.noFilter.setBackgroundResource(R.drawable.filter_selected_shape);
            binding.highToLowFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
            binding.lowToHighFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });

        binding.highToLowFilter.setOnClickListener(v -> {
            filterData(1);
            binding.highToLowFilter.setBackgroundResource(R.drawable.filter_selected_shape);
            binding.noFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
            binding.lowToHighFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });

        binding.lowToHighFilter.setOnClickListener(v -> {
            filterData(2);
            binding.lowToHighFilter.setBackgroundResource(R.drawable.filter_selected_shape);
            binding.noFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
            binding.highToLowFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });




        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        notesViewModel.getAllNotesVm.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filterNameList = notes;
            }
        });

    }




    private void filterData(int i) {
        if (i == 0) {
            notesViewModel.getAllNotesVm.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNameList = notes;
                }
            });
        } else if (i == 1) {
            notesViewModel.highToLowVm.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNameList = notes;
                }
            });

        } else if (i == 2) {
            notesViewModel.lowToHighVm.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNameList = notes;
                }
            });
        }
    }


    private void setAdapter(List<Notes> notesList) {
        // binding.notesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        // We use  StaggeredGrid layout because our notes hight will not be same , so our notes height will be effected 

        binding.notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(this, notesList);
        binding.notesRecyclerView.setAdapter(notesAdapter);
    }
















    // Menu for search ==============================


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu_toolbar,menu);
        MenuItem menuItem = menu.findItem(R.id.search_notes);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search notes Here...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Jo text hm type krengy wo hmen newText me mileag =========
                // Hmen realtime me chapter by character data milega
                // For example:
                // S
                // Se
                // Sea
                // Sear
                // Mean keyboard me type pe hmne esa results milega
                filterNotes(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void filterNotes(String newText) {
        Toast.makeText(this, ""+newText, Toast.LENGTH_SHORT).show();
        ArrayList<Notes> filterNames = new ArrayList<>();

        for(Notes notes : this.filterNameList)
        {
            // our notes title, and the saerch field we search is match or contain

            if(notes.notesTitle.contains(newText) || notes.notesSubtitle.contains(newText))
            {
               //IT show in our new list 
                filterNames.add(notes);
            }
            this.notesAdapter.searchNotes(filterNames);
        }

    }

}
