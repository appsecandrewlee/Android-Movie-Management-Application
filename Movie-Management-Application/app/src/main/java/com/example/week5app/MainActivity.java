package com.example.week5app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week5app.contentprovider.MovieDataBaseAttributes;
import com.example.week5app.contentprovider.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView nav;
    ListView listView;
    ArrayList<String> dataSource;
    ArrayAdapter<String> adapter;

    EditText SearchTitle;
    EditText YearNum;
    EditText CountryName;
    EditText Genre;
    EditText Cost;
    EditText Keywords;
    EditText Actors;
    EditText plusone;
    TextView getXcords;

    Button clear;
    String SHARE_PREFS = "shared_prefs";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapters; //maybe adapter

    ArrayList<Item> items;
    public static final String ITEMS_KEY = "ITEMS_KEY";
    public static final String ITEMS_SP_FILE_NAME = "SP_FILE_NAME";

    private MovieViewModel Mmoviewmodel;
    int x;
    int y;
    int fx;
    int fy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        items = new ArrayList<>();
        SearchTitle = findViewById(R.id.SearchTitle);
        YearNum = findViewById(R.id.YearNum);
        CountryName = findViewById(R.id.CountryName);
        Genre = findViewById(R.id.Genre);
        Cost = findViewById(R.id.Cost);
        Keywords = findViewById(R.id.Keywords);
        Actors = findViewById(R.id.Actors);
        getXcords =findViewById(R.id.getX);

        View views = findViewById(R.id.frame_layout_id);
        views.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
               /* int x = (int) event.getX();
                int y = (int) event.getY();
                int fx= (int) event.getX();
                int fy = (int) event.getY();  */

                    //if you have a swipe, you have two points, when you touch down you get the first point
                    //lift your finger, gets the second point
                    //starting point is down,
                   
                   

                //horizontal movement needs to be large
                int action = event.getActionMasked();
                switch (action) {
                    case (MotionEvent.ACTION_DOWN):

                        x = (int)event.getX();
                        y = (int)event.getY();
                        return true;
                    case (MotionEvent.ACTION_MOVE):

                        return true;
                    case (MotionEvent.ACTION_UP):
                        fx =(int) event.getX();
                        fy =(int) event.getY();

                      if(fx - x >= 50){
                          addItemToList();
                      }else if (y - fy >=50){
                          SearchTitle.setText(""); //using the setText method to clear,
                          YearNum.getText().clear(); //taught by tutors to clear function using clear() method
                          CountryName.setText("");
                          Genre.setText("");
                          Cost.setText("");
                          Keywords.setText("");
                          Actors.setText("");

                      }else

                        return true;

                    default:
                        return false;
                }
            }
        });


        //week 5
        toolbar = findViewById(R.id.toolbar_id);
        drawerLayout = findViewById(R.id.drawer_layout_id);
        nav = findViewById(R.id.nav_id);
        listView = findViewById(R.id.listview_id);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(this);
        dataSource = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.listview_item_layout, dataSource);
        listView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addItemToList();
            }
        });

        //button clear
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(view -> {
            SearchTitle.setText(""); //using the setText method to clear,
            YearNum.getText().clear(); //taught by tutors to clear function using clear() method
            CountryName.setText("");
            Genre.setText("");
            Cost.setText("");
            Keywords.setText("");
            Actors.setText("");
        });

        Mmoviewmodel = new ViewModelProvider(this).get(MovieViewModel.class);

//        recyclerView = findViewById(R.id.rec_id);
//        layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);

//        adapters.
//        adapters = new MyRecyclerAdapter()

//        recyclerView.setAdapter(adapters);

        //week 8


    }
        //select * from Movies;


    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences(SHARE_PREFS, 0);
        SearchTitle.findViewById(R.id.SearchTitle);
        String stringFromPrefs = prefs.getString("saveString", "Default Title");
        SearchTitle.setText(stringFromPrefs);

        SharedPreferences prefs2 = getSharedPreferences(SHARE_PREFS, 0);
        YearNum.findViewById(R.id.YearNum);
        String integerFromPrefs = prefs2.getString("saveNumber", "");
        YearNum.setText(integerFromPrefs);

        SharedPreferences prefs3 = getSharedPreferences(SHARE_PREFS, 0);
        CountryName.findViewById(R.id.CountryName);
        String CountryNameFromPrefs = prefs3.getString("saveCountryName", "Default Country");
        CountryName.setText(CountryNameFromPrefs);

        SharedPreferences prefs4 = getSharedPreferences(SHARE_PREFS, 0);
        Genre.findViewById(R.id.Genre);
        String GenreFromPrefs = prefs4.getString("saveGenre", "Default Genre");
        Genre.setText(GenreFromPrefs);

        SharedPreferences prefs5 = getSharedPreferences(SHARE_PREFS, 0);
        Cost.findViewById(R.id.Cost);
        String CostFromPrefs = prefs5.getString("saveCost", "");
        Cost.setText(CostFromPrefs);

        SharedPreferences prefs6 = getSharedPreferences(SHARE_PREFS, 0);
        Keywords.findViewById(R.id.Keywords);
        String KeyFromPrefs = prefs6.getString("saveKey", "");
        Keywords.setText(KeyFromPrefs);

        SharedPreferences prefs7 = getSharedPreferences(SHARE_PREFS, 0);
        Actors.findViewById(R.id.Actors);
        String TextNumFromPrefs = prefs7.getString("saveTextNum", "");
        Actors.setText(TextNumFromPrefs);

        SharedPreferences prefs8 = getSharedPreferences(SHARE_PREFS, 0);
        plusone = findViewById(R.id.plusone);
        String plusone1 = prefs8.getString("Plusoneeverytime", "");
        plusone.setText(plusone1);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_list, menu);
        return true;
    }

    @Override
    protected void onResume() {  //dont think this is needed
        super.onResume();
    }

    @Override
    protected void onPause() { //dont think this is needed
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences(SHARE_PREFS, 0); //persistent data store
        SearchTitle = findViewById(R.id.SearchTitle);
        String stringRetrieved = SearchTitle.getText().toString();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("saveString", stringRetrieved);
        editor.apply();

        SharedPreferences prefs2 = getSharedPreferences(SHARE_PREFS, 0);
        YearNum = findViewById(R.id.YearNum);
        String NumberRetrieved = YearNum.getText().toString();
        SharedPreferences.Editor editor2 = prefs2.edit();
        editor2.putString("saveNumber", NumberRetrieved);
        editor2.apply();

        SharedPreferences prefs3 = getSharedPreferences(SHARE_PREFS, 0);
        CountryName = findViewById(R.id.CountryName);
        String CountryRetrieved = CountryName.getText().toString();
        SharedPreferences.Editor editor3 = prefs3.edit();
        editor3.putString("saveCountryName", CountryRetrieved);
        editor3.apply();

        SharedPreferences prefs4 = getSharedPreferences(SHARE_PREFS, 0);
        Genre = findViewById(R.id.Genre);
        String GenreRetrieved = Genre.getText().toString();
        SharedPreferences.Editor editor4 = prefs4.edit();
        editor4.putString("saveGenre", GenreRetrieved);
        editor4.apply();

        SharedPreferences prefs5 = getSharedPreferences(SHARE_PREFS, 0);
        Cost = findViewById(R.id.Cost);
        String CostRetrieved = Cost.getText().toString();
        SharedPreferences.Editor editor5 = prefs5.edit();
        editor5.putString("saveCost", CostRetrieved);
        editor5.apply();

        SharedPreferences prefs6 = getSharedPreferences(SHARE_PREFS, 0);
        Keywords = findViewById(R.id.Keywords);
        String KeyRetrieved = Keywords.getText().toString();
        SharedPreferences.Editor editor6 = prefs6.edit();
        editor6.putString("saveKey", KeyRetrieved);
        editor6.apply();


        SharedPreferences prefs7 = getSharedPreferences(SHARE_PREFS, 0);
        Actors = findViewById(R.id.Actors);
        String TextNumRetrieved = Actors.getText().toString();
        SharedPreferences.Editor editor7 = prefs7.edit();
        editor7.putString("saveTextNum", TextNumRetrieved);
        editor7.apply();

        SharedPreferences prefs8 = getSharedPreferences(SHARE_PREFS, 0);
        plusone = findViewById(R.id.plusone);
        String plusone1 = plusone.getText().toString();
        SharedPreferences.Editor editor8 = prefs8.edit();
        editor8.putString("savePlusoneeverytime", plusone1);
        editor8.apply();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    } //ondestroy is not needed either

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    } //onbackpressed isnot needed either

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        SharedPreferences pref_upper = getSharedPreferences(SHARE_PREFS, 0);
        SearchTitle = findViewById(R.id.SearchTitle);
        String UppercaseSearch = pref_upper.getString("saveString", "default UpperString");
        UppercaseSearch = UppercaseSearch.toUpperCase();
        SearchTitle.setText(UppercaseSearch);
        SharedPreferences.Editor edit1 = pref_upper.edit();
        edit1.putString("saveString", UppercaseSearch);
        edit1.apply();

        SharedPreferences pref_lower = getSharedPreferences(SHARE_PREFS, 0);
        Genre = findViewById(R.id.Genre);
        String LowercaseSearch = pref_lower.getString("saveGenre", "default LowerString");
        LowercaseSearch = LowercaseSearch.toLowerCase();
        Genre.setText(LowercaseSearch);
        SharedPreferences.Editor LowEdit = pref_lower.edit();
        LowEdit.putString("saveGenre", LowercaseSearch);
        LowEdit.apply();

        SharedPreferences prefs8 = getSharedPreferences(SHARE_PREFS, 0);
        plusone = findViewById(R.id.plusone);
        String plusone1 = prefs8.getString("savePlusoneeverytime", "");
        plusone.setText(plusone1);
        SharedPreferences.Editor plusonedit = prefs8.edit();
        plusonedit.putString("savePlusoneeverytime", plusone1);
        plusonedit.apply();

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle inState) {
        super.onRestoreInstanceState(inState);

        SharedPreferences pref_upper = getSharedPreferences(SHARE_PREFS, 0);
        SearchTitle = findViewById(R.id.SearchTitle);
        String UppercaseSearch = pref_upper.getString("saveString", "default UpperString");
        UppercaseSearch = UppercaseSearch.toUpperCase();
        SearchTitle.setText(UppercaseSearch);
        SharedPreferences.Editor edit1 = pref_upper.edit();
        edit1.putString("saveString", UppercaseSearch);
        edit1.apply();

        SharedPreferences pref_lower = getSharedPreferences(SHARE_PREFS, 0);
        Genre = findViewById(R.id.Genre);
        String LowercaseSearch = pref_lower.getString("saveGenre", "");
        LowercaseSearch = LowercaseSearch.toLowerCase();
        Genre.setText(LowercaseSearch);
        SharedPreferences.Editor LowEdit = pref_lower.edit();
        LowEdit.putString("saveGenre", LowercaseSearch);
        LowEdit.apply();

        SharedPreferences prefs8 = getSharedPreferences(SHARE_PREFS, 0);
        plusone = findViewById(R.id.plusone);
        String plusone1 = prefs8.getString("savePlusoneeverytime", "");
        plusone.setText(plusone1);
        SharedPreferences.Editor plusonedit = prefs8.edit();
        plusonedit.putString("savePlusoneeverytime", plusone1);
        plusonedit.apply();

    }

    public void buttonClicked(View view) {


        EditText SearchFunc = findViewById(R.id.SearchTitle); //Resources find id called SearchTitle using the search Edittext a form of overlay that is in conjunction with PlainText
        String Display = SearchFunc.getText().toString(); //declare string as a variable, and give it a name called display
        EditText plusoneText = findViewById(R.id.plusone);
        String Display2 = plusoneText.getText().toString();
        String SearchbarAdd = SearchTitle.getText().toString();
        String YearNumAdd = YearNum.getText().toString();
        dataSource.add(SearchbarAdd + " | " + YearNumAdd);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "will add" + Display + Display2 + "has been added", Toast.LENGTH_SHORT).show();
        if (plusoneText.getText().toString().equals("")) {
            plusoneText.setText(String.valueOf(1));
        } else {

            plusoneText.setText(String.valueOf(Integer.parseInt(plusone.getText().toString()) + 1));

        }

    }

    public void add1000(View view) {
        EditText costText = findViewById(R.id.Cost); //Where you enter the cost
        if (costText.getText().toString().equals("")) {
            costText.setText(String.valueOf(1000));
        } else {
            costText.setText(String.valueOf(Integer.parseInt(costText.getText().toString()) + 1000));
        }


    }

    class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);

            StringTokenizer sT = new StringTokenizer(msg, ";");
            String SearchTitleSMS = sT.nextToken();
            String YearSumSMS = sT.nextToken();
            String CountryNameSMS = sT.nextToken();
            String GenreSMS = sT.nextToken();
            String CostSMS = sT.nextToken();
            String KeywordsSMS = sT.nextToken();
            String ActorsSMS = sT.nextToken();

            int value1 = Integer.valueOf(YearSumSMS);
            int value2 = Integer.valueOf(ActorsSMS);
            int finalValue = value1 + value2;


            SearchTitle.setText(SearchTitleSMS);
            YearNum.setText(finalValue + "");
            CountryName.setText(CountryNameSMS);
            Genre.setText(GenreSMS);
            Cost.setText(CostSMS);
            Keywords.setText(KeywordsSMS);

        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.options_menu_clear_all_id) {
            clearAll();
            SearchTitle.setText(""); //using the setText method to clear,
            YearNum.getText().clear(); //taught by tutors to clear function using clear() method
            CountryName.setText("");
            Genre.setText("");
            Cost.setText("");
            Keywords.setText("");
            Actors.setText("");
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.drawer_layout_add_item_id:
                addItemToList();
                break;
            case R.id.drawer_layout_remove_last_item_id:
                removeLastItem();
                break;
            case R.id.drawer_layout_id:
                removeAllItems();
            case R.id.drawer_layout_id_listallmovies:
                ListAllMovies();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addItemToList() {
        String SearchbarAdd = SearchTitle.getText().toString();
        String YearNumAdd = YearNum.getText().toString();
        dataSource.add(SearchbarAdd + " | " + YearNumAdd);
        adapter.notifyDataSetChanged();

        MovieDataBaseAttributes movieDataBaseAttributes = new MovieDataBaseAttributes(SearchTitle.getText().toString(),Integer.parseInt(YearNum.getText().toString()),CountryName.getText().toString(),Genre.getText().toString(),Integer.parseInt(Cost.getText().toString()));
        Mmoviewmodel.insert(movieDataBaseAttributes);

       // items.add(new Item(SearchTitle.getText().toString(),12,CountryName.getText().toString(),Genre.getText().toString(),12,Keywords.getText().toString(),Actors.getText().toString(),12));
        Log.d("dustin","added " + items.size());
    }

    private void removeLastItem() {
        if (dataSource.size() > 0) {
            dataSource.remove(dataSource.size() - 1);
            adapter.notifyDataSetChanged();
        }
    }

    private void removeAllItems() {
        if (dataSource.size() > 0) {
            dataSource.clear();
            adapter.notifyDataSetChanged();
            Mmoviewmodel.deleteAll();

        }
    }

    private void clearAll() {
        dataSource.clear();
        adapter.notifyDataSetChanged();
        Mmoviewmodel.deleteAllMoviesInRecord();


    }
    private void ListAllMovies() {

        /* Gson gson = new Gson();
        SharedPreferences sP = getSharedPreferences(ITEMS_SP_FILE_NAME, MODE_PRIVATE);
        String dbStr = gson.toJson(items);
        Log.d("dustinn",items + "");
        SharedPreferences.Editor edit = sP.edit();
        edit.putString(ITEMS_KEY, dbStr);
        edit.apply(); */


        Intent intent = new Intent(this, UsingGson.class);
        startActivity(intent);

    }


}