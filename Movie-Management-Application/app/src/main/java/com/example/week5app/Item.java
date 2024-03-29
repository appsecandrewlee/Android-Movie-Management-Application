package com.example.week5app;

public class Item {
    private String GetSearchTitle;
    private float GetYearNum;
    private String GetCountryName;
    private String GetGenre;
    private float GetCost;
    private String GetKeyword;
    private String GetActors;
    private float GetPlusOne;

    public Item(String Search, int Num, String Country, String Genre, float Cost, String Key, String Actrs, int plus) {
        this.GetSearchTitle = Search;
        this.GetYearNum = Num;
        this.GetCountryName = Country;
        this.GetGenre = Genre;
        this.GetCost = Cost;
        this.GetKeyword = Key;
        this.GetActors = Actrs;
        this.GetPlusOne = plus;
    }

    public String getGetSearchTitle() {
        return GetSearchTitle;
    }

    public float getGetYearNum() { return GetYearNum; }

    public String getGetCountryName() {
        return GetCountryName;
    }

    public String getGetGenre() {
        return GetGenre;
    }

    public float getGetCost() {
        return GetCost;
    }

    public String getGetKeyword() {
        return GetKeyword;
    }

    public String getGetActors() {
        return GetActors;
    }

    public float getGetPlusOne() {
        return GetPlusOne;
    }

}




