package com.example.week5app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week5app.contentprovider.MovieDataBaseAttributes;
import com.example.week5app.contentprovider.MovieViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsingGson extends AppCompatActivity {

    ArrayList<Item> items;
    RecyclerView recyclerView;
    MovieViewModel mmoviemodel;

    MyRecyclerAdapter adapters;

    List<MovieDataBaseAttributes> listData2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_gson);

//        Gson gson=new Gson();
//
//        SharedPreferences sP=getSharedPreferences(MainActivity.ITEMS_SP_FILE_NAME,MODE_PRIVATE);
//        String dbStr=sP.getString(MainActivity.ITEMS_KEY,"");
//        Log.d("andrewhelp", dbStr);
//
//        Type type = new TypeToken<ArrayList<Item>>() {}.getType();
//        items = gson.fromJson(dbStr,type);
//
//

        recyclerView = findViewById(R.id.rec_id);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        mmoviemodel = new ViewModelProvider(this).get(MovieViewModel.class);

        mmoviemodel.getAllCustomers().observe(this, newData -> {
            adapters = new MyRecyclerAdapter(newData);
            adapters.notifyDataSetChanged();
            recyclerView.setAdapter(adapters);
        });
        //null object reference was the error, i returned the wrong attributes listdata1, instead of listdata2
        //pk issue, didn't set the unique ID for movie table
    }
}