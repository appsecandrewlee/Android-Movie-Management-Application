package com.example.week5app.contentprovider;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Year;


@Entity(tableName = "MovieAttri")
public class MovieDataBaseAttributes {
    public static final String MovieAttri = "MovieAttri";
    public static final String Column_ID = BaseColumns._ID;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id; //used to identify a certain object in the database
    @ColumnInfo(name = "YearNumDBName")
    private int YearNumDataBase;
    @ColumnInfo(name = "SearchTitleDBName")
    private String SearchTitleDataBase;
    @ColumnInfo(name = "CountryDBName")
    private String CountryDataBase;
    @ColumnInfo(name = "GenreDBName")
    private String GenreDataBase;
    @ColumnInfo(name = "CostDBName")
    private int CostDataBase;

    public MovieDataBaseAttributes(String SearchTitleDataBase, int YearNumDataBase, String CountryDataBase, String GenreDataBase,int CostDataBase) {
        this.SearchTitleDataBase = SearchTitleDataBase;
        this.YearNumDataBase = YearNumDataBase;
        this.CountryDataBase = CountryDataBase;
        this.GenreDataBase = GenreDataBase;
        this.CostDataBase = CostDataBase;
    }

    public String getSearchTitleDataBase() {
        return SearchTitleDataBase;
    }

    public int getYearNumDataBase() {
        return YearNumDataBase;
    }

    public String getCountryDataBase() {
        return CountryDataBase;
    }

    public int getCostDataBase() {return CostDataBase;}

    public String getGenreDataBase(){return GenreDataBase;}

    public int getId() {
        return id;
    } //getter for certain object

    public void setId(int id) {
        this.id = id;
    } //setter for object

    public String toString(){
    return "Movies{" + "id =" + id
            +",Searchtitle =" + SearchTitleDataBase + '\'' +
            "Year =" + YearNumDataBase + "Country =" + CountryDataBase + GenreDataBase +CostDataBase;
    }
}