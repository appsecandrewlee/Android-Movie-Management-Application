package com.example.week5app;

import java.util.ArrayList;

public class DataBridge {
    private ArrayList<Item> items;

    private DataBridge() {
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    private static DataBridge instance;

    public static DataBridge getInstance() {

        if (instance == null) {
            instance = new DataBridge();
        }
        return instance;
    }
}
