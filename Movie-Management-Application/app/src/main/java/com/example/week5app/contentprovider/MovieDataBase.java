package com.example.week5app.contentprovider;


import android.content.Context;
import android.os.strictmode.InstanceCountViolation;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MovieDataBaseAttributes.class}, version = 3)
public abstract class MovieDataBase extends RoomDatabase {

    public static final String MovieDataBaseAttributes_Name = "Movie_DataBase";

    public abstract MovieDAO movieDAO();

    private static volatile MovieDataBase INSTANCE;

    private static final int Number_of_threads = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(Number_of_threads);

    public static MovieDataBase getDataBase(Context context){
        if (INSTANCE == null){
            synchronized (MovieDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MovieDataBase.class, MovieDataBaseAttributes_Name).fallbackToDestructiveMigration().build();

                }
            }
        }
        return INSTANCE;
    }

}
