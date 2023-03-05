package com.example.notetakingappmvvm.RoomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notetakingappmvvm.Dao.DaoClass;
import com.example.notetakingappmvvm.Model.Notes;


// Database ko entity require he jo hmne bnyi he Notes.class
// ,, Next database ka version dena he (jb hm database me kxh changing kre to version change krengy 2 )

@Database(entities = {Notes.class},version = 1)
public abstract class NotesDb extends RoomDatabase {

    // create abstract method of Dao
    public abstract DaoClass notesDoa();

    // create and Instance of the class
    public static NotesDb INSTANCE;

    public static NotesDb getDatabaseInstance(Context context)
    {
        // ye function hmne NotesDb return krega so hmne is NotesDb ka instance return krwana he

        if(INSTANCE == null)
        {
          // agr instance null he mean hmara instance create nhi hua SO hmne instance ko create krwana he
            // First hmne Context dena he
            // Second hmne Room Database ki class provide krni he
            // Third hmne Database ka name provide krna he jo Entity class me diya tha
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDb.class,
                    "Notes_Db").allowMainThreadQueries().build();

            // So hmara long running operations keliye Main Thread block hojyega agr hm krte he
            // So hm allow krengy Main Thread Queries ko > .allowMainThreadQueries()
        }

        return INSTANCE;
    }

}
