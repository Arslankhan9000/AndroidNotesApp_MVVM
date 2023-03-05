package com.example.notetakingappmvvm.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Db")
public class Notes {

    // Attibutes or Columns Require in Notes

    // Auto generate Key mean there value will be auto incremented
    
    @PrimaryKey(autoGenerate = true)
    public int id;


    // Other Columns of Our Table in Database 

    @ColumnInfo(name = "notes_Title")
    public String notesTitle;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "notes_SubTitle")
    public String notesSubtitle;

    @ColumnInfo(name = "notes_Date")
    public String notesDate;

    @ColumnInfo(name = "notes_Priority")
    public String notesPriority;

}
