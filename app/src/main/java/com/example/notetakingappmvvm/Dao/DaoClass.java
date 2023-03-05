package com.example.notetakingappmvvm.Dao;
// Need to Specify that this is our Dao Class like in Entity case

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notetakingappmvvm.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface DaoClass {

    // We need to perform some queries in database to add,update, dlete data   >> Ctrl+Space to get Database name
    // We also need a List where our all attributes define 
   

    // 1. ============================= Select Data or fetch data
    @Query("SELECT * FROM Notes_Db")
    //List<Notes> getAllNotes();
    // Or For LiveData
    LiveData<List<Notes>> getAllNotes();


    //2.  ============Insert
    // Insert Data in notes 
    @Insert
    public void insertNotes(Notes... notes);


    // 3. ========================= Delete
     // We delete the note using there id the id is get through query 
     
    @Query("DELETE FROM Notes_Db WHERE id=:id")
    public void deleteNotes(int id);



    // 4. ========================= Update
    @Update
    public void updateNotes(Notes notes);



    // Filter Notes I.e High to low and low to high by priority

    @Query("SELECT * FROM Notes_Db ORDER BY notes_Priority DESC")
        // starts from priority 10 to 1 up son on...
        // Or For LiveData
    LiveData<List<Notes>> highToLow();


    @Query("SELECT * FROM Notes_Db ORDER BY notes_Priority ASC")
        // starts from priority 1 to 10 up son on...
        // Or For LiveData
    LiveData<List<Notes>> lowToHigh();


}
