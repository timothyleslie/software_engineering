package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.roombasic.database.converters.Converters;

@Database(entities = {Bill.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class BillDatabase extends RoomDatabase {

    private static BillDatabase INSTANCE;
    static synchronized BillDatabase getDatabase (Context context){
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),BillDatabase.class, "bill_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    public abstract BillDao getBillDao();


}
