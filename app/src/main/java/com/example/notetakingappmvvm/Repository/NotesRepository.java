package com.example.notetakingappmvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notetakingappmvvm.Dao.DaoClass;
import com.example.notetakingappmvvm.Model.Notes;
import com.example.notetakingappmvvm.RoomDb.NotesDb;

import java.util.List;

public class NotesRepository {

    // In NotesRepository We perform our actions here like update, delete, or insert notes and fitler 

    public DaoClass notesDaoR;

    public LiveData<List<Notes>> getAllNotesR;

    // Filter notes =================

    public LiveData<List<Notes>> lowToHighR;

    public LiveData<List<Notes>> highToLowR;

    // Now we perform some operations on Database 
    // With the help of Dao we can insert and Delete the data

    // Hmne Database ka instance chahiye

    public NotesRepository (Application application)
    {
        NotesDb notesDatabase = NotesDb.getDatabaseInstance(application);

        notesDaoR = notesDatabase.notesDoa();

        getAllNotesR = notesDaoR.getAllNotes();

        // Filter notes
        lowToHighR = notesDaoR.lowToHigh();
        highToLowR = notesDaoR.highToLow();


    }

    public void insertNotesR(Notes notes)
    {
        notesDaoR.insertNotes(notes);
    }

    public void deleteNotesR(int id)
    {
        notesDaoR.deleteNotes(id);
    }

    public void updateNotesR(Notes notes)
    {
        notesDaoR.updateNotes(notes);
    }

}
