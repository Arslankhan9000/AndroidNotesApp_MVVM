package com.example.notetakingappmvvm.Dao;
// jese hmne Entity me btaya tha ye ik Entity he to hmne is interface class me bhii btatma prega ye hmari dao he

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notetakingappmvvm.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface DaoClass {

    // Hmne data lo get Krwana he from database jiskeliye hm query likhngy    >> Ctrl+Space to get Database name
    // Hmne ik list chahiye jime Notes keliye sare attributes hot


    // 1. ============================= Select Data or fetch data
    @Query("SELECT * FROM Notes_Db")
    //List<Notes> getAllNotes();
    // Or For LiveData
    LiveData<List<Notes>> getAllNotes();


    //2.  ============Insert
    // ab hmne insert keliye function bnanan he  >> ye notes automatically get hojyega
    @Insert
    public void insertNotes(Notes... notes);


    // 3. ========================= Delete
    // Ab hm delete krengy using id to hm query ka use krengy
    // ye jo id hm dere he function me wo query me get hojyegi
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
