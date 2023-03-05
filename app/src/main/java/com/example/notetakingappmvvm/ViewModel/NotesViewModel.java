package com.example.notetakingappmvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notetakingappmvvm.Model.Notes;
import com.example.notetakingappmvvm.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repositoryVm;
    public LiveData<List<Notes>> getAllNotesVm;

    // Filter notes =======================
    public LiveData<List<Notes>> lowToHighVm;
    public LiveData<List<Notes>> highToLowVm;

    public NotesViewModel(Application application) {
        super(application);

        repositoryVm = new NotesRepository(application);

        getAllNotesVm = repositoryVm.getAllNotesR;

        // ============================================ Filter Notes
        lowToHighVm = repositoryVm.lowToHighR;
        highToLowVm = repositoryVm.highToLowR;

    }

    public void insertNotesVm(Notes notes)
    {
        repositoryVm.insertNotesR(notes);
    }

    public void deleteNotesVm(int id)
    {
        repositoryVm.deleteNotesR(id);
    }

    public void updateNotesVm(Notes notes)
    {
        repositoryVm.updateNotesR(notes);
    }




}
