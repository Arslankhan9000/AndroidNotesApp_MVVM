package com.example.notetakingappmvvm.RoomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notetakingappmvvm.Dao.DaoClass;
import com.example.notetakingappmvvm.Model.Notes;


// A Database require an entity that we make in Notes.class
// Next We define a database version to 1 initially, i.e after some times if we make changes in database stucture we can change the version to 2

@Database(entities = {Notes.class},version = 1)
public abstract class NotesDb extends RoomDatabase {

    // create abstract method of Dao
    public abstract DaoClass notesDoa();

    // create and Instance of the class
    public static NotesDb INSTANCE;

    public static NotesDb getDatabaseInstance(Context context)
    {
        // This function return an instance of Notes Db class 

        if(INSTANCE == null)
        {
          // If our instance is null so this mean our instance is null and we need to create it
            // First we provide a context
            // Second We provide a room database class
            // Third we provide a database name 
            
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDb.class,
                    "Notes_Db").allowMainThreadQueries().build();

            // For long running operatios our main thread will be block so we need to allow 
            // a Main Thread Queries  > .allowMainThreadQueries()
        }

        return INSTANCE;
    }

}
