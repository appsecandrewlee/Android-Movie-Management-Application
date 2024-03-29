package com.example.week5app.contentprovider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDAO {
    @Query("SELECT * from MovieAttri")
    LiveData<List<MovieDataBaseAttributes>> getAllMovieAttributes();

//    @Query("select * from MovieAttri where SearchTitleDBName=:SearchTitleDataBase" )
//    List<MovieDataBaseAttributes> getMovieAttributes(String SearchTitleDataBase);

    @Insert
    void addMovies(MovieDataBaseAttributes moviedatabaseAttributes);

    @Query("delete FROM MovieAttri")
    void deleteAllMovies();

    @Query("delete FROM Movieattri where CostDBName and CostDBName < 1000 ")
    void deleteitems();

}
