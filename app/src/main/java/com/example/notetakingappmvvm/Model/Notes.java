package com.example.notetakingappmvvm.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Db")
public class Notes {

    // Notes me hmne jo jo Attributes or Columns chaiye

    // Auto generate Key mean iski value khud increment hogi or ye unique hoga to hm isko primary key consider krengy
    @PrimaryKey(autoGenerate = true)
    public int id;


    // Ye hmare baki columns hongy database me jese SQL me hote he

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
