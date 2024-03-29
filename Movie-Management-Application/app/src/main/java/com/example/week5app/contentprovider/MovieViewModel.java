package com.example.week5app.contentprovider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class MovieViewModel extends AndroidViewModel {
    private MovieRepository mRepository;
    private LiveData<List<MovieDataBaseAttributes>> mAllMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MovieRepository(application);
        mAllMovies = mRepository.getmAllMovies(); //get al
    }

    public LiveData<List<MovieDataBaseAttributes>> getAllCustomers() {
        return mAllMovies;
    }

    public void insert(MovieDataBaseAttributes moviedatabase) {
        mRepository.insert(moviedatabase);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void deleteAllMoviesInRecord(){
        mRepository.deleteAllMoviesInRecord();
    }
}






